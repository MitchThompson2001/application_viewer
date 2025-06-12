package com.example.application_viewer.specifications;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.example.application_viewer.components.NoteType;
import com.example.application_viewer.models.Patient;
import com.example.application_viewer.models.PatientNote;

import jakarta.persistence.criteria.Predicate;

public class PatientNoteSpecification {

    public static Specification<PatientNote> filterBy(
        Long id,
        Patient patient,
        LocalDate loggedDate,
        NoteType noteType,
        String note,
        LocalDateTime auditDate,
        String lastUpdatedBy,
        LocalDate lastUpdatedDate
    ) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (id != null) predicates.add(cb.equal(root.get("id"), id));
            if (patient != null) predicates.add(cb.equal(root.get("patient"), patient));
            if (loggedDate != null) predicates.add(cb.equal(root.get("loggedDate"), loggedDate));
            if (noteType != null) predicates.add(cb.equal(root.get("noteType"), noteType));
            if (note != null) predicates.add(cb.like(cb.lower(root.get("note")), "%" + note.toLowerCase() + "%"));
            if (auditDate != null) predicates.add(cb.equal(root.get("auditDate"), auditDate));
            if (lastUpdatedBy != null) predicates.add(cb.like(cb.lower(root.get("lastUpdatedBy")), "%" + lastUpdatedBy.toLowerCase() + "%"));
            if (lastUpdatedDate != null) predicates.add(cb.equal(root.get("lastUpdatedDate"), lastUpdatedDate));

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
    
}
