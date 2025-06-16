package com.example.application_viewer.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.application_viewer.components.PatientType;
import com.example.application_viewer.components.SexType;
import com.example.application_viewer.models.Patient;
import com.example.application_viewer.models.PatientDemographic;
import com.example.application_viewer.repositories.PatientDemographicRepository;
import com.example.application_viewer.specifications.PatientDemographicSpecification;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PatientDemographicService {
    @Autowired private PatientDemographicRepository patientDemographicRepository;

    public List<PatientDemographic> getAllPatients() {
        return patientDemographicRepository.findAll();
    }

    public List<PatientDemographic> searchAndSortPatientDemographics(
        Long id,
        Patient patient,
        PatientType patientType,
        Boolean active,
        String firstName,
        String lastName,
        String origin,
        String emailA,
        String emailB,
        LocalDate dob,
        String phoneA,
        String phoneB,
        String ssnLastFour,
        String height,
        String weight,
        SexType sex,
        String notes,
        String homeCompanyNum,
        String homeCompanyLoc,
        LocalDate lastUpdated,
        String sortField,
        String sortDir
    ) {
        Specification<PatientDemographic> spec = PatientDemographicSpecification.filterBy(
            id, 
            patient, 
            patientType, 
            active, 
            firstName, 
            lastName, 
            origin, 
            emailA, 
            emailB,
            dob, 
            phoneA, 
            phoneB, 
            ssnLastFour, 
            height, 
            weight, 
            sex, 
            notes,
            homeCompanyNum, 
            homeCompanyLoc, 
            lastUpdated
        );

        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortField);

        return patientDemographicRepository.findAll(spec, sort);
    }

    public PatientDemographic findByID(long id) {
        return patientDemographicRepository.findById(id)
            .orElseThrow(() -> 
            new EntityNotFoundException("Entity not found for ID " + id));
    }

    public PatientDemographic findByPatient(Patient patient) {
        return patientDemographicRepository.findByPatient(patient)
            .orElseThrow(() -> 
            new EntityNotFoundException("Entity not found for patient " + patient.getId()));
    }
}
