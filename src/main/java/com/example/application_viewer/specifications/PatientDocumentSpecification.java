package com.example.application_viewer.specifications;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.example.application_viewer.components.DocumentClass;
import com.example.application_viewer.models.Patient;
import com.example.application_viewer.models.PatientAuthAndCert;
import com.example.application_viewer.models.PatientDocument;

import jakarta.persistence.criteria.Predicate;

public class PatientDocumentSpecification {

    public static Specification<PatientDocument> filterBy(
        Long id,
        Patient patient,
        String fileName,
        String filePath,
        LocalDate uploadDate,
        DocumentClass documentClass,
        Byte[] pdfFile,
        String notes,
        LocalDateTime auditDate,
        String lastUpdatedBy,
        LocalDate lastUpdatedDate,
        PatientAuthAndCert patientAuthAndCert
    ) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (id != null) predicates.add(cb.equal(root.get("id"), id));
            if (patient != null) predicates.add(cb.equal(root.get("patient"), patient));
            if (fileName != null) predicates.add(cb.like(cb.lower(root.get("fileName")), "%" + fileName.toLowerCase() + "%"));
            if (filePath != null) predicates.add(cb.like(cb.lower(root.get("filePath")), "%" + filePath.toLowerCase() + "%"));
            if (uploadDate != null) predicates.add(cb.equal(root.get("uploadDate"), uploadDate));
            if (documentClass != null) predicates.add(cb.equal(root.get("documentClass"), documentClass));
            if (pdfFile != null) predicates.add(cb.equal(root.get("pdfFile"), pdfFile));
            if (notes != null) predicates.add(cb.like(cb.lower(root.get("notes")), "%" + notes.toLowerCase() + "%"));
            if (auditDate != null) predicates.add(cb.equal(root.get("auditDate"), auditDate));
            if (lastUpdatedBy != null) predicates.add(cb.like(cb.lower(root.get("lastUpdatedBy")), "%" + lastUpdatedBy.toLowerCase() + "%"));
            if (patientAuthAndCert != null) predicates.add(cb.equal(root.get("patientAuthAndCert"), patientAuthAndCert));

            return cb.and(predicates.toArray(Predicate[]::new));
        };
    }
    
}
