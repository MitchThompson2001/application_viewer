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
