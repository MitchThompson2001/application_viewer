/*
 * Name: Mitchell Thompson
 * File: PatientDemographicRepository.java
 * Project: Data Viewer Application
 */

package com.example.application_viewer.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.application_viewer.models.Patient;
import com.example.application_viewer.models.PatientDemographic;

@Repository
public interface PatientDemographicRepository extends 
    JpaRepository<PatientDemographic, Long>, 
    JpaSpecificationExecutor<PatientDemographic> {

        
        public Optional<PatientDemographic> findByPatient(Patient patient);
}
