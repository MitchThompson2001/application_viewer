package com.example.application_viewer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.application_viewer.models.Patient;
import com.example.application_viewer.models.PatientDiagnosisCode;
import com.example.application_viewer.repositories.PatientDiagnosisCodeRepository;
import com.example.application_viewer.specifications.PatientDiagnosisCodeSpecification;

@Service
public class PatientDiagnosisCodeService {

    @Autowired private PatientDiagnosisCodeRepository patientDiagnosisCodeRepository;

    public List<PatientDiagnosisCode> searchPatientDiagnosisCodes(
        Long id,
        Patient patient,
        String code,
        String description,
        String sortField,
        String sortDir) {

        Specification<PatientDiagnosisCode> spec = PatientDiagnosisCodeSpecification.filterBy(
            id, 
            patient, 
            code, 
            description);

        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortField);

        return patientDiagnosisCodeRepository.findAll(spec, sort);
    }
    
}
