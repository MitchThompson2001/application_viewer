package com.example.application_viewer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.application_viewer.models.PatientContact;

@Repository
public interface PatientContactRepository extends 
    JpaRepository<PatientContact, Long>, 
    JpaSpecificationExecutor<PatientContact> {
    
}
