package com.example.application_viewer.specifications;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.example.application_viewer.models.PatientDemographic;
import com.example.application_viewer.components.PatientType;
import com.example.application_viewer.components.SexType;
import com.example.application_viewer.models.Patient;

public class PatientDemographicSpecification {

    public static Specification<PatientDemographic> filterBy(
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
        LocalDate lastUpdated
    ) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (id != null) predicates.add(cb.equal(root.get("id"), id));
            if (patient != null) predicates.add(cb.equal(root.get("patient"), patient));
            if (patientType != null) predicates.add(cb.equal(root.get("patientType"), patientType));
            if (active != null) predicates.add(cb.equal(root.get("active"), active));
            if (firstName != null) predicates.add(cb.like(cb.lower(root.get("firstName")), "%" + firstName.toLowerCase() + "%"));
            if (lastName != null) predicates.add(cb.like(cb.lower(root.get("lastName")), "%" + lastName.toLowerCase() + "%"));
            if (origin != null) predicates.add(cb.like(cb.lower(root.get("origin")), "%" + origin.toLowerCase() + "%"));
            if (emailA != null) predicates.add(cb.like(cb.lower(root.get("emailA")), "%" + emailA.toLowerCase() + "%"));
            if (emailB != null) predicates.add(cb.like(cb.lower(root.get("emailB")), "%" + emailB.toLowerCase() + "%"));
            if (dob != null) predicates.add(cb.equal(root.get("dob"), dob));
            if (phoneA != null) predicates.add(cb.like(root.get("phoneA"), "%" + phoneA + "%"));
            if (phoneB != null) predicates.add(cb.like(root.get("phoneB"), "%" + phoneB + "%"));
            if (ssnLastFour != null) predicates.add(cb.like(root.get("ssnLastFour"), "%" + ssnLastFour + "%"));
            if (height != null) predicates.add(cb.like(root.get("height"), "%" + height + "%"));
            if (weight != null) predicates.add(cb.like(root.get("weight"), "%" + weight + "%"));
            if (sex != null) predicates.add(cb.equal(root.get("sex"), sex));
            if (notes != null) predicates.add(cb.like(cb.lower(root.get("notes")), "%" + notes.toLowerCase() + "%"));
            if (homeCompanyNum != null) predicates.add(cb.like(cb.lower(root.get("homeCompanyNum")), "%" + homeCompanyNum.toLowerCase() + "%"));
            if (homeCompanyLoc != null) predicates.add(cb.like(cb.lower(root.get("homeCompanyLoc")), "%" + homeCompanyLoc.toLowerCase() + "%"));
            if (lastUpdated != null) predicates.add(cb.equal(root.get("lastUpdated"), lastUpdated));

            return cb.and(predicates.toArray(Predicate[]::new));
        };
    }
}
