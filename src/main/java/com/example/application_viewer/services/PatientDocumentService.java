package com.example.application_viewer.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.application_viewer.components.DocumentClass;
import com.example.application_viewer.models.Patient;
import com.example.application_viewer.models.PatientAuthAndCert;
import com.example.application_viewer.models.PatientDocument;
import com.example.application_viewer.repositories.PatientDocumentRepository;
import com.example.application_viewer.specifications.PatientDocumentSpecification;

@Service
public class PatientDocumentService {
    @Autowired private PatientDocumentRepository patientDocumentRepository;

    public List<PatientDocument> searchAndSortPatientDocuments(
        Long id,
        Patient patient,
        String fileName,
        String filePath,
        LocalDate uploadDate,
        DocumentClass documentClass,
        Byte[] pdfFile,
        String notes,
        LocalDateTime auditDate,
        String lastUpdatedBy,
        LocalDate lastUpdatedDate,
        PatientAuthAndCert patientAuthAndCert,
        String sortField,
        String sortDir
    ) {
        Specification<PatientDocument> spec = PatientDocumentSpecification.filterBy(
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
            patientAuthAndCert
        );

        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortField);

        return patientDocumentRepository.findAll(spec, sort);
    }   
    
}
