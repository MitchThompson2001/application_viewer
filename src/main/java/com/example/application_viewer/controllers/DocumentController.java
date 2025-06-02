package com.example.application_viewer.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.application_viewer.models.Document;
import com.example.application_viewer.models.Patient;
import com.example.application_viewer.repositories.DocumentRepository;
import com.example.application_viewer.repositories.PatientRepository;
import com.example.application_viewer.services.DocumentHistoryService;
import com.example.application_viewer.services.DocumentService;
import com.example.application_viewer.services.FileStorageService;

/*
 * Controller class that maintains PDF documents uploaded to the local
 * directory.
 */
@Controller
public class DocumentController {

    @Autowired private PatientRepository patientRepository;

    @Autowired private DocumentRepository documentRepository;

    @Autowired private FileStorageService fileStorageService;

    @Autowired private DocumentService documentService;

    @Autowired private DocumentHistoryService documentHistoryService;

    @GetMapping("/document_list")
        public String listAllDocuments(Model model) {
            List<Document> documents = documentService.listAllDocuments();
            model.addAttribute("documents", documents);

            return "documentList";
}


    @GetMapping("/documents/view/{fileName:.+}")
    public ResponseEntity<Resource> viewFile(@PathVariable String fileName) throws MalformedURLException {
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        documentHistoryService.recordAction(fileName, "VIEW");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"") // "inline" to open in browser
                .body(resource);
    }

    @GetMapping("/documents/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) throws MalformedURLException {
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        documentHistoryService.recordAction(fileName, "DOWNLOAD");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"") // attachment = download
                .body(resource);
    }

    @GetMapping("/upload_document")
    public String showUploadPage() {
        return "uploadDocument";
    }

    @PostMapping("/upload")
    public String handleUpload(
            @RequestParam("patientId") Long patientId,
            @RequestParam("file") MultipartFile file,
            Model model) {

        if (file == null || file.isEmpty() || file.getContentType() == null || 
            !file.getContentType().equalsIgnoreCase("application/pdf")) {

            model.addAttribute("message", "Only PDF files are allowed.");
            model.addAttribute("messageType", "danger");
            return "uploadDocument";
        }

        try {
            // Check if patient exists
            Patient patient = patientRepository.findById(patientId)
                    .orElseThrow(() -> new IllegalArgumentException("Patient not found"));

            String storedFileName = fileStorageService.storeFile(file);

            Document doc = new Document();
            doc.setFileName(file.getOriginalFilename());
            doc.setFilePath(storedFileName);
            doc.setUploadDate(LocalDate.now());
            doc.setPatient(patient);
            doc.setPdfFile(file.getBytes());

            documentRepository.save(doc);

            model.addAttribute("message", "Upload successful!");
            model.addAttribute("messageType", "success");
        } catch (IllegalArgumentException e) {
            model.addAttribute("message", e.getMessage()); // "Patient not found"
            model.addAttribute("messageType", "danger");
        } catch (Exception e) {
            model.addAttribute("message", "Upload failed: " + e.getMessage());
            model.addAttribute("messageType", "danger");
            e.printStackTrace();
        }

        return "uploadDocument";
    }


    @PostMapping("/patients/{patientId}/upload")
    public String uploadDocument(@PathVariable Long patientId,
                                  @RequestParam("file") MultipartFile file) throws IOException {
        Patient patient = patientRepository.findById(patientId)
            .orElseThrow(() -> new RuntimeException("Patient not found"));

        String storedFileName = fileStorageService.storeFile(file);

        Document doc = new Document();
        doc.setFileName(file.getOriginalFilename());
        // doc.setPdfFile(file.getBytes());
        doc.setFilePath(storedFileName);
        doc.setUploadDate(LocalDate.now());
        doc.setPatient(patient);

        documentRepository.save(doc);

        return "redirect:/patients/" + patientId;
    }

    @GetMapping("/patients/{patientId}")
    public String showPatientDocuments(@PathVariable Long patientId, Model model) {
        Patient patient = patientRepository.findById(patientId)
            .orElseThrow(() -> new RuntimeException("Patient not found"));

        model.addAttribute("patient", patient);
        model.addAttribute("documents", documentRepository.findByPatientId(patientId));
        return "patientDocuments";
    }

    @GetMapping("/documents/{id}/download")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) throws MalformedURLException {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));

        Resource resource = fileStorageService.loadFileAsResource(document.getFilePath());

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getFileName() + "\"")
                .body(resource);
    }

    // @GetMapping("/documents/{id}/download")
    // public ResponseEntity<byte[]> downloadDocument(@PathVariable Long id) {
    //     Document document = documentRepository.findById(id)
    //         .orElseThrow(() -> new RuntimeException("Document not found"));

    //     return ResponseEntity.ok()
    //         .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getFileName() + "\"")
    //         .contentType(MediaType.APPLICATION_PDF)
    //         .body(document.getPdfFile());
    // }
}

