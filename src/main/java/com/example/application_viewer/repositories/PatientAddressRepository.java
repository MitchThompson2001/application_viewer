package com.example.application_viewer.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.application_viewer.models.Patient;
import com.example.application_viewer.models.PatientAddress;

@Repository
public interface PatientAddressRepository extends 
    JpaRepository<PatientAddress, Long>, 
    JpaSpecificationExecutor<PatientAddress> {

        public Optional<PatientAddress> findByPatient(Patient patient);
    
}
