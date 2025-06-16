/*
 * Name: Mitchell Thompson
 * File: PatientService.java
 * Project: Data Viewer Application
 */

package com.example.application_viewer.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.application_viewer.models.Location;
import com.example.application_viewer.models.Patient;
import com.example.application_viewer.models.PatientAddress;
import com.example.application_viewer.models.PatientAttribute;
import com.example.application_viewer.models.PatientAuthAndCert;
import com.example.application_viewer.models.PatientContact;
import com.example.application_viewer.models.PatientDemographic;
import com.example.application_viewer.models.PatientDiagnosisCode;
import com.example.application_viewer.models.PatientDocument;
import com.example.application_viewer.models.PatientInsurance;
import com.example.application_viewer.models.PatientNote;
import com.example.application_viewer.models.PatientOrder;
import com.example.application_viewer.models.PatientTicket;
import com.example.application_viewer.models.PatientTransaction;
import com.example.application_viewer.repositories.PatientRepository;
import com.example.application_viewer.specifications.PatientSpecification;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PatientService {
    
    @Autowired private PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public List<Patient> searchAndSortPatients(
        Long id,
        PatientAddress patientAddress,
        PatientAttribute patientAttribute,
        PatientAuthAndCert patientAuthAndCert,
        PatientContact patientContact,
        PatientDemographic patientDemographic,
        PatientDiagnosisCode patientDiagnosisCode,
        PatientDocument patientDocument,
        PatientInsurance patientInsurance,
        PatientNote patientNote,
        PatientOrder patientOrder,
        PatientTicket patientTicket,
        PatientTransaction patientTransaction,
        Location location,
        String lastUpdatedBy,
        LocalDate lastUpdatedDate,
        String sortField, 
        String sortDir) {

        Specification<Patient> spec = PatientSpecification.filterBy(
            id, 
            patientAddress, 
            patientAttribute, 
            patientAuthAndCert, 
            patientContact, 
            patientDemographic, 
            patientDiagnosisCode, 
            patientDocument, 
            patientInsurance, 
            patientNote, 
            patientOrder, 
            patientTicket, 
            patientTransaction,
            location,
            lastUpdatedBy,
            lastUpdatedDate
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

    public Patient findById(long id) {
        return patientRepository.findById(id)
            .orElseThrow(() -> 
            new EntityNotFoundException("Entity not found for ID " + id));
    }

    public void updatePatient(Patient updatedPatient) {
        
    }
    
    // public void deleteByID(long id) {
    //     patientRepository.deleteById(id);
    // }
}
