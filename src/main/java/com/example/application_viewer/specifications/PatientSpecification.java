package com.example.application_viewer.specifications;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.example.application_viewer.models.Patient;
import com.example.application_viewer.models.PatientDemographic;

public class PatientSpecification {
    
    public static Specification<Patient> filterBy(
        Long id,
        PatientDemographic patientDemographic
    ) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (id != null) predicates.add(cb.equal(root.get("id"), id));
            if (patientDemographic != null) predicates.add(cb.equal(root.get("patientDemographic"), patientDemographic));

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
