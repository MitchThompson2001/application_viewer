package com.example.application_viewer.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.application_viewer.components.DocumentClass;
import com.example.application_viewer.models.Patient;
import com.example.application_viewer.models.PatientAuthAndCert;
import com.example.application_viewer.models.PatientDocument;
import com.example.application_viewer.services.PatientDocumentService;

@Controller
public class PatientDocumentController {
    @Autowired private PatientDocumentService patientDocumentService;

    @GetMapping("/patient_document_list")
    public String getPatientDocuments(
        @RequestParam(required = false) Long id,
        @RequestParam(required = false) Patient patient,
        @RequestParam(required = false) String fileName,
        @RequestParam(required = false) String filePath,
        @RequestParam(required = false) LocalDate uploadDate,
        @RequestParam(required = false) DocumentClass documentClass,
        @RequestParam(required = false) Byte[] pdfFile,
        @RequestParam(required = false) String notes,
        @RequestParam(required = false) LocalDateTime auditDate,
        @RequestParam(required = false) String lastUpdatedBy,
        @RequestParam(required = false) LocalDate lastUpdatedDate,
        @RequestParam(required = false) PatientAuthAndCert patientAuthAndCert,
        @RequestParam(required = false, defaultValue = "id") String sortField,
        @RequestParam(required = false, defaultValue = "asc") String sortDir,
        Model model
    ) {

        List<PatientDocument> documents = patientDocumentService.searchAndSortPatientDocuments(
            id, 
            patient, 
            fileName, 
            filePath, 
            uploadDate, 
            documentClass, 
            pdfFile, 
            notes, 
            auditDate, 
            lastUpdatedBy, 
            lastUpdatedDate, 
            patientAuthAndCert, 
            sortField, 
            sortDir
        );

        model.addAttribute("allPatDocList", documents);
        model.addAttribute("id", id);
        model.addAttribute("patient", patient);
        model.addAttribute("fileName", fileName);
        model.addAttribute("filePath", filePath);
        model.addAttribute("uploadDate", uploadDate);
        model.addAttribute("documentClass", documentClass);
        model.addAttribute("pdfFile", pdfFile);
        model.addAttribute("notes", notes);
        model.addAttribute("auditDate", auditDate);
        model.addAttribute("lastUpdatedBy", lastUpdatedBy);
        model.addAttribute("lastUpdatedDate", lastUpdatedDate);
        model.addAttribute("patientAuthAndCert", patientAuthAndCert);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "patientDocumentList";
    }
    
    
}
