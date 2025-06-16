package com.example.application_viewer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.application_viewer.components.AddressType;
import com.example.application_viewer.components.ResidenceType;
import com.example.application_viewer.models.Patient;
import com.example.application_viewer.models.PatientAddress;
import com.example.application_viewer.repositories.PatientAddressRepository;
import com.example.application_viewer.specifications.PatientAddressSpecification;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PatientAddressService {
    @Autowired private PatientAddressRepository patientAddressRepository;

    public List<PatientAddress> searchAndSortPatientAddresses(
        Long id,
        Patient patient,
        Boolean active,
        AddressType addressType,
        ResidenceType residenceType,
        String addressA,
        String addressB,
        String city,
        String state,
        String county,
        String zipCode,
        String country,
        String geoCode,
        String electricCompany,
        String serviceLocNum,
        String sortField,
        String sortDir) {
            
        Specification<PatientAddress> spec = PatientAddressSpecification.filterBy(
        id,
        patient,
        active,
        addressType,
        residenceType,
        addressA,
        addressB,
        city,
        state,
        county,
        zipCode,
        country,
        geoCode,
        electricCompany,
        serviceLocNum);

        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortField);
        return patientAddressRepository.findAll(spec, sort);
    }

    public PatientAddress findByPatient(Patient patient) {
        return patientAddressRepository.findByPatient(patient)
            .orElseThrow(() -> 
            new EntityNotFoundException("Entity not found for patient " + patient.getId()));
    }
}
