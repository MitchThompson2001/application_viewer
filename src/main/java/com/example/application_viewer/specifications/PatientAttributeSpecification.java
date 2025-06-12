package com.example.application_viewer.specifications;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import jakarta.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.example.application_viewer.models.Patient;
import com.example.application_viewer.models.PatientAttribute;

public class PatientAttributeSpecification {

    public static Specification<PatientAttribute> filterBy(
        Long id,
        Patient patient,
        Boolean active,
        LocalDate effectiveDate,
        String attribute) {
            return (root, query, cb) -> {
                List<Predicate> predicates = new ArrayList<>();

                if (id != null) predicates.add(cb.equal(root.get("id"), id));
                if (patient != null) predicates.add(cb.equal(root.get("patient"), patient));
                if (active != null) predicates.add(cb.equal(root.get("active"), active));
                if (effectiveDate != null) predicates.add(cb.equal(root.get("effectiveDate"), effectiveDate));
                if (attribute != null) predicates.add(cb.like(cb.lower(root.get("attribute")), "%" + attribute.toLowerCase() + "%"));
                
                return cb.and(predicates.toArray(Predicate[]::new));
            };
        }
}
