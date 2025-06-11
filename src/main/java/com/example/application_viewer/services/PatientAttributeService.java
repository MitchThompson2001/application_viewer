package com.example.application_viewer.services;

import java.util.List;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.application_viewer.models.Patient;
import com.example.application_viewer.models.PatientAttribute;
import com.example.application_viewer.repositories.PatientAttributeRepository;
import com.example.application_viewer.specifications.PatientAttributeSpecification;

@Service
public class PatientAttributeService {

    @Autowired private PatientAttributeRepository patientAttributeRepository;

    public List<PatientAttribute> searchAndSortPatientAttributes(
        Long id,
        Patient patient,
        Boolean active,
        LocalDate effectiveDate,
        String attribute,
        String sortField,
        String sortDir) {

        Specification<PatientAttribute> spec = PatientAttributeSpecification.filterBy(
            id,
            patient,
            active,
            effectiveDate,
            attribute);

        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortField);
        return patientAttributeRepository.findAll(spec, sort);
    }

}
