/*
 * Name: Mitchell Thompson
 * File: DocumentService.java
 * Project: Data Viewer Application
 */

package com.example.application_viewer.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.application_viewer.models.Document;
import com.example.application_viewer.repositories.DocumentRepository;

@Service
public class DocumentService {
    
    @Autowired private DocumentRepository documentRepository;

    public List<Document> listPatientDocuments(Long id) {
        return documentRepository.findByPatientId(id);
    }
    
    public List<Document> listAllDocuments() {
        return documentRepository.findAllWithPatient();
    }

    public List<Document> searchAndSortDocuments(
        Long id,
        Long patientId,
        String fileName,
        LocalDate uploadDate,
        String sortField,
        String sortDir) {

        Specification<Document> spec = (root, query, cb) -> cb.conjunction();

        if (id != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("id"), id));
        }

        if (patientId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("patient").get("id"), patientId));
        }

        if (fileName != null && !fileName.isEmpty()) {
        spec = spec.and((root, query, cb) -> 
            cb.like(cb.lower(root.get("fileName")), 
            "%" + fileName + "%"));
        }

        if (uploadDate != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("uploadDate"), uploadDate));
        }

         Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortField);

         return documentRepository.findAll(spec, sort); 
    }
}
