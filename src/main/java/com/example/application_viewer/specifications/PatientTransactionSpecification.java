package com.example.application_viewer.specifications;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.example.application_viewer.components.BillType;
import com.example.application_viewer.components.TransactionSubType;
import com.example.application_viewer.components.TransactionType;
import com.example.application_viewer.models.Location;
import com.example.application_viewer.models.Patient;
import com.example.application_viewer.models.PatientOrder;
import com.example.application_viewer.models.PatientTransaction;

import jakarta.persistence.criteria.Predicate;

public class PatientTransactionSpecification {
    public static Specification<PatientTransaction> filterBy(
        Long id,
        Location location,
        Patient patient,
        PatientOrder patientOrder,
        String hcpc,
        BillType billType,
        Double cost,
        TransactionType transactionType,
        TransactionSubType transactionSubType,
        LocalDate transactionDate,
        String insuranceId,
        String insuranceName,
        String claimNumber,
        String notes,
        LocalDateTime auditDate,
        LocalDateTime auditUpdateDate
    ) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (id != null) predicates.add(cb.equal(root.get("id"), id));
            if (location != null) predicates.add(cb.equal(root.get("location"), location));
            if (patient != null) predicates.add(cb.equal(root.get("patient"), patient));
            if (patientOrder != null) predicates.add(cb.equal(root.get("patientOrder"), patientOrder));
            if (hcpc != null) predicates.add(cb.equal(root.get("hcpc"), hcpc));
            if (billType != null) predicates.add(cb.equal(root.get("billType"), billType));
            if (cost != null) predicates.add(cb.equal(root.get("cost"), cost));
            if (transactionType != null) predicates.add(cb.equal(root.get("transactionType"), transactionType));
            if (transactionSubType != null) predicates.add(cb.equal(root.get("transactionSubType"), transactionSubType));
            if (transactionDate != null) predicates.add(cb.equal(root.get("transactionDate"), transactionDate));
            if (insuranceId != null) predicates.add(cb.equal(root.get("insuranceId"), insuranceId));
            if (insuranceName != null) predicates.add(cb.like(cb.lower(root.get("insuranceName")), "%" + insuranceName.toLowerCase() + "%"));
            if (claimNumber != null) predicates.add(cb.equal(root.get("claimNumber"), claimNumber));
            if (notes != null) predicates.add(cb.like(cb.lower(root.get("notes")), "%" + notes.toLowerCase() + "%"));
            if (auditDate != null) predicates.add(cb.equal(root.get("auditDate"), auditDate));
            if (auditUpdateDate != null) predicates.add(cb.equal(root.get("auditUpdateDate"), auditUpdateDate));

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
