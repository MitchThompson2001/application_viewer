/*
 * Name: Mitchell Thompson
 * File: DocumentController.java
 * Project: Data Viewer Application
 */

package com.example.application_viewer.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.example.application_viewer.services.PatientService;

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

    @Autowired private PatientService patientService;


    /*
     * Directs to the documentList html
     */
    @GetMapping("/document_list")
        public String searchDocuments(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Long patientId,
            @RequestParam(required = false) String fileName,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate uploadDate,
            @RequestParam(required = false, defaultValue = "id") String sortField,
            @RequestParam(required = false, defaultValue = "asc") String sortDir,
            Model model) {

            boolean hasSearchInput = Stream.of(id, patientId, fileName, uploadDate).anyMatch(Objects::nonNull);

            List<Document> documents;
            if (hasSearchInput) {
                documents = documentService.searchAndSortDocuments(
                    id,
                    patientId,
                    fileName,
                    uploadDate,
                    sortField,
                    sortDir);
            } else {
                documents = documentService.searchAndSortDocuments(
                    null,
                    null,
                    null,
                    null,
                    sortField,
                    sortDir);
            }
            model.addAttribute("allDocList", documents);
            model.addAttribute("id", id);
            model.addAttribute("patientId", patientId);
            model.addAttribute("fileName", fileName);
            model.addAttribute("uploadDate", uploadDate);
            model.addAttribute("sortField", sortField);
            model.addAttribute("sortDir", sortDir);
            model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

            return "documentList";
    }

    /*
     * View docs for a specific ID
     */
    @GetMapping("/document_list/{id}")
    public String searchPatientDocuments(
        @PathVariable Long id,
        @RequestParam(required = false) String fileName,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate uploadDate,
        @RequestParam(required = false, defaultValue = "id") String sortField,
        @RequestParam(required = false, defaultValue = "asc") String sortDir,
        Model model) {

        boolean hasSearchInput = Stream.of(id, fileName, uploadDate).anyMatch(Objects::nonNull);

        List<Document> documents;
        if (hasSearchInput) {
            documents = documentService.searchAndSortDocuments(
                null,
                id,
                fileName,
                uploadDate,
                sortField,
                sortDir);
        } else {
            documents = documentService.searchAndSortDocuments(
                null,
                null,
                null,
                null,
                sortField,
                sortDir);
        }

        String firstName = patientService.getPatientFirstNameById(id);

        model.addAttribute("documents", documents);
        model.addAttribute("patientId", id);
        model.addAttribute("fileName", fileName);
        model.addAttribute("uploadDate", uploadDate);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("title", firstName + "'s Documents");

        return "patientDocumentList";
    }

    /*
     * Allows a user to view a PDF stored in the database without downloading
     */
    @GetMapping("/documents/view/{fileName:.+}")
    public ResponseEntity<Resource> viewFile(@PathVariable String fileName) throws MalformedURLException {
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        documentHistoryService.recordAction(fileName, "VIEW");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"") // "inline" to open in browser
                .body(resource);
    }
    /*
     * Allows a user to download a PDF from the computer to their local device
     */
    @GetMapping("/documents/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) throws MalformedURLException {
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        documentHistoryService.recordAction(fileName, "DOWNLOAD");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"") // attachment = download
                .body(resource);
    }

    /*
     * Directs to the uploadDocument html
     */
    @GetMapping("/upload_document")
    public String showUploadPage() {
        return "uploadDocument";
    }

    /*
     * Upload page where ID is already attached due to working within a profile
     */
    @GetMapping("/upload_document/{id}")
    public String showPatientUploadPage(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "uploadDocumentPatient";
    }

    /*
     * Allows a user to upload a PDF file from their local device
     * to the database attached to a specific patient ID
     */
    @PostMapping("/upload")
    public String handleUpload(
            @RequestParam("patientId") Long patientId,
            @RequestParam("file") MultipartFile file,
            Model model) {

        if (file == null || file.isEmpty()) {
            model.addAttribute("message", "Only PDF files are allowed.");
            model.addAttribute("messageType", "danger");
            return "uploadDocument";
        }

        String contentType = file.getContentType();
        if (contentType == null || !contentType.equalsIgnoreCase("application/pdf")) {
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
        } catch (IllegalArgumentException | IOException e) {
            model.addAttribute("message", "Upload failed: " + e.getMessage());
            model.addAttribute("messageType", "danger");
        }

        return "uploadDocument";
    }
}

