package com.example.application_viewer.specifications;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.example.application_viewer.components.EmailType;
import com.example.application_viewer.components.PhoneType;
import com.example.application_viewer.components.RelationshipType;
import com.example.application_viewer.models.Patient;
import com.example.application_viewer.models.PatientContact;

import jakarta.persistence.criteria.Predicate;

public class PatientContactSpecification {

    public static Specification<PatientContact> filterBy(
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
        String notes) {

        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (id != null) predicates.add(cb.equal(root.get("id"), id));
            if (patient != null) predicates.add(cb.equal(root.get("patient"), patient));
            if (firstName != null) predicates.add(cb.like(cb.lower(root.get("firstName")), "%" + firstName.toLowerCase() + "%"));
            if (lastName != null) predicates.add(cb.like(cb.lower(root.get("lastName")), "%" + lastName.toLowerCase() + "%"));
            if (relationshipType != null) predicates.add(cb.equal(root.get("relationshipType"), relationshipType));
            if (phone != null) predicates.add(cb.equal(root.get("phone"), phone));
            if (phoneType != null) predicates.add(cb.equal(root.get("phoneType"), phoneType));
            if (email != null) predicates.add(cb.like(cb.lower(root.get("email")), "%" + email.toLowerCase() + "%"));
            if (emailType != null) predicates.add(cb.equal(root.get("emailType"), emailType));
            if (emergencyContact != null) predicates.add(cb.equal(root.get("emergencyContact"), emergencyContact));
            if (notes != null) predicates.add(cb.like(cb.lower(root.get("notes")), "%" + notes.toLowerCase() + "%"));

            return cb.and(predicates.toArray(new Predicate[0]));
        };

    }
    
}
