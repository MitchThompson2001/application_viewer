package com.example.application_viewer.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.application_viewer.models.Document;


@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByPatientId(Long patientId);
    
    @Query("SELECT d FROM Document d JOIN FETCH d.patient")
    List<Document> findAllWithPatient();
}