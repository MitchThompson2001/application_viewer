package com.example.application_viewer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Patient> searchPatients(String firstName, String lastName, String email, String dob, String phone) {
        // Convert empty strings to null to work with the query
        firstName = (firstName == null || firstName.isEmpty()) ? null : firstName;
        lastName = (lastName == null || lastName.isEmpty()) ? null : lastName;
        email = (email == null || email.isEmpty()) ? null : email;
        dob = (dob == null || dob.isEmpty()) ? null : dob;
        phone = (phone == null || phone.isEmpty()) ? null : phone;
        
        return patientRepository.searchPatients(firstName, lastName, email, dob, phone);
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
