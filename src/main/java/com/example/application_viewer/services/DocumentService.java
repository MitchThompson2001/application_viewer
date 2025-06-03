/*
 * Name: Mitchell Thompson
 * File: DocumentService.java
 * Project: Data Viewer Application
 */

package com.example.application_viewer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.application_viewer.models.Document;
import com.example.application_viewer.repositories.DocumentRepository;

@Service
public class DocumentService {
    
    @Autowired private DocumentRepository documentRepository;
    
    public List<Document> listAllDocuments() {
        return documentRepository.findAllWithPatient();
    }
}
