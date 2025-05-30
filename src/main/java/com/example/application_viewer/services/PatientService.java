package com.example.application_viewer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.application_viewer.models.Patient;
import com.example.application_viewer.repositories.PatientRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Sort;

@Service
public class PatientService {
    
    @Autowired private PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public List<Patient> searchAndSortPatients(String firstName, String lastName, String email, String dob, String phone, String sortField, String sortDir) {
        // Convert empty strings to null to work with the query
        // firstName = (firstName == null || firstName.isEmpty()) ? null : firstName;
        // lastName = (lastName == null || lastName.isEmpty()) ? null : lastName;
        // email = (email == null || email.isEmpty()) ? null : email;
        // dob = (dob == null || dob.isEmpty()) ? null : dob;
        // phone = (phone == null || phone.isEmpty()) ? null : phone;

        Specification<Patient> spec = Specification.where(null);

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
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("dob")), "%" + dob.toLowerCase() + "%"));
        }

        if (phone != null && !phone.isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("phone")), "%" + phone.toLowerCase() + "%"));
        }

        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortField);
        return patientRepository.findAll(spec, sort);    
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
