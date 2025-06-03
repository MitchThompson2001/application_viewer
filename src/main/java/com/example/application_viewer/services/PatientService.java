/*
 * Name: Mitchell Thompson
 * File: PatientService.java
 * Project: Data Viewer Application
 */

package com.example.application_viewer.services;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.application_viewer.models.Patient;
import com.example.application_viewer.repositories.PatientRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PatientService {
    
    @Autowired private PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public List<Patient> searchAndSortPatients(
        Long id,
        String firstName, 
        String lastName, 
        String email, 
        String dob, 
        String phone, 
        String sortField, 
        String sortDir) {

        Specification<Patient> spec = (root, query, cb) -> cb.conjunction();

        if (id != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("id"), id));
        }

        if (firstName != null && !firstName.isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("firstName")), "%" + firstName.toLowerCase() + "%"));
        }

        if (lastName != null && !lastName.isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("lastName")), "%" + lastName.toLowerCase() + "%"));
        }

        if (email != null && !email.isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("email")), "%" + email.toLowerCase() + "%"));
        }

        if (dob != null && !dob.isEmpty()) {
            try {
                LocalDate dobDate = LocalDate.parse(dob);  // parse the date string to LocalDate
                spec = spec.and((root, query, cb) -> cb.equal(root.get("dob"), dobDate));
            } catch (DateTimeParseException e) {
                // Handle invalid date format if needed, or ignore filter
                System.out.println("Invalid dob format: " + dob);
            }
        }

        if (phone != null && !phone.isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("phone")), "%" + phone + "%"));
        }

        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortField);
        return patientRepository.findAll(spec, sort);
    }

    public String getPatientFirstNameById(Long id) {
        return patientRepository.findById(id)
                .map(Patient::getFirstName)
                .orElse("Unknown");
    }


    public void save(Patient patient) {
        patientRepository.save(patient);
    }

    public Patient getByID(long id) {
        return patientRepository.findById(id)
            .orElseThrow(() -> 
            new EntityNotFoundException("Entity not found for ID " + id));
    }
    
    public void deleteByID(long id) {
        patientRepository.deleteById(id);
    }
}
