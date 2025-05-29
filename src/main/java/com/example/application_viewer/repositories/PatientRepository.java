package com.example.application_viewer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.application_viewer.models.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    
}
