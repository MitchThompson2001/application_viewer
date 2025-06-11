/*
 * Name: Mitchell Thompson
 * File: PatientService.java
 * Project: Data Viewer Application
 */

package com.example.application_viewer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.application_viewer.models.Patient;
import com.example.application_viewer.models.PatientDemographic;
import com.example.application_viewer.repositories.PatientRepository;
import com.example.application_viewer.specifications.PatientSpecification;

@Service
public class PatientService {
    
    @Autowired private PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public List<Patient> searchAndSortPatients(
        Long id,
        PatientDemographic patientDemographic, 
        String sortField, 
        String sortDir) {

        Specification<Patient> spec = PatientSpecification.filterBy(
            id, patientDemographic
        );

        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortField);
        return patientRepository.findAll(spec, sort);
    }

    // public String getPatientFirstNameById(Long id) {
    //     return patientRepository.findById(id)
    //             .map(Patient::getFirstName)
    //             .orElse("Unknown");
    // }


    // public void save(Patient patient) {
    //     patientRepository.save(patient);
    // }

    // public Patient getByID(long id) {
    //     return patientRepository.findById(id)
    //         .orElseThrow(() -> 
    //         new EntityNotFoundException("Entity not found for ID " + id));
    // }
    
    // public void deleteByID(long id) {
    //     patientRepository.deleteById(id);
    // }
}
