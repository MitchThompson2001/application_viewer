package com.example.application_viewer.specifications;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.example.application_viewer.models.Patient;
import com.example.application_viewer.models.PatientDiagnosisCode;

import jakarta.persistence.criteria.Predicate;

public class PatientDiagnosisCodeSpecification {

    public static Specification<PatientDiagnosisCode> filterBy(
        Long id,
        Patient patient,
        String code,
        String description
    ) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (id != null) predicates.add(cb.equal(root.get("id"), id));
            if (patient != null) predicates.add(cb.equal(root.get("patient"), patient));
            if (code != null) predicates.add(cb.equal(root.get("code"), code));
            if (description != null) predicates.add(cb.like(root.get("description"), "%" + description + "%"));
            
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
    
}
