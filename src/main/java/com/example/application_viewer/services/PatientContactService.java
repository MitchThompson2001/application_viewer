package com.example.application_viewer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.application_viewer.components.EmailType;
import com.example.application_viewer.components.PhoneType;
import com.example.application_viewer.components.RelationshipType;
import com.example.application_viewer.models.Patient;
import com.example.application_viewer.models.PatientContact;
import com.example.application_viewer.repositories.PatientContactRepository;
import com.example.application_viewer.specifications.PatientContactSpecification;

@Service
public class PatientContactService {
    @Autowired private PatientContactRepository patientContactRepository;

    public List<PatientContact> searchAndPatientContacts(
        Long id,
        Patient patient,
        String firstName,
        String lastName,
        RelationshipType relationshipType,
        String phone,
        PhoneType phoneType,
        String email,
        EmailType emailType,
        Boolean emergencyContact,
        String notes,
        String sortField,
        String sortDir) {
        
        Specification<PatientContact> spec = PatientContactSpecification.filterBy(
            id, 
            patient, 
            firstName, 
            lastName, 
            relationshipType, 
            phone, 
            phoneType, 
            email, 
            emailType, 
            emergencyContact, 
            notes);

        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortField);

        return patientContactRepository.findAll(spec, sort);
    }
}
