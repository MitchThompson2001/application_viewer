package com.example.application_viewer.specifications;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.example.application_viewer.components.AuthorizationLimitType;
import com.example.application_viewer.components.AuthorizationType;
import com.example.application_viewer.components.BillType;
import com.example.application_viewer.components.OrderType;
import com.example.application_viewer.models.Patient;
import com.example.application_viewer.models.PatientAuthAndCert;
import com.example.application_viewer.models.PatientDocument;
import com.example.application_viewer.models.PatientOrder;

import jakarta.persistence.criteria.Predicate;

public class PatientAuthAndCertSpecification {
    public static Specification<PatientAuthAndCert> filterBy(
        Long id,
        Patient patient,
        PatientOrder patientOrder,
        OrderType orderType,
        String authCertNumber,
        AuthorizationType authCertType,
        String planCode,
        PatientDocument patientDocument,
        BillType billType,
        String hcpcsCode,
        LocalDate ticketDos,
        String description,
        LocalDate startDate,
        LocalDate dueDate,
        String receiverFirstName,
        String receiverLastName,
        String receiverPosition,
        Integer duration,
        String bypassReason,
        String notes,
        LocalDateTime auditDate,
        String authorizationLimit,
        AuthorizationLimitType authorizationLimitType,
        String lastUpdatedBy,
        LocalDate lastUpdatedDate
    ) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (id != null) predicates.add(cb.equal(root.get("id"), id));
            if (patient != null) predicates.add(cb.equal(root.get("patient"), patient));
            if (patientOrder != null) predicates.add(cb.equal(root.get("patientOrder"), patientOrder));
            if (orderType != null) predicates.add(cb.equal(root.get("orderType"), orderType));
            if (authCertNumber != null) predicates.add(cb.equal(root.get("authCertNumber"), authCertNumber));
            if (authCertType != null) predicates.add(cb.equal(root.get("authCertType"), authCertType));
            if (planCode != null) predicates.add(cb.equal(root.get("planCode"), planCode));
            if (patientDocument != null) predicates.add(cb.equal(root.get("patientDocument"), patientDocument));
            if (billType != null) predicates.add(cb.equal(root.get("billType"), billType));
            if (hcpcsCode != null) predicates.add(cb.equal(root.get("hcpcsCode"), hcpcsCode));
            if (ticketDos != null) predicates.add(cb.equal(root.get("ticketDos"), ticketDos));
            if (description != null) predicates.add(cb.like(cb.lower(root.get("description")), "%" + description.toLowerCase() + "%"));
            if (startDate != null) predicates.add(cb.equal(root.get("startDate"), startDate));
            if (dueDate != null) predicates.add(cb.equal(root.get("dueDate"), dueDate));
            if (receiverFirstName != null) predicates.add(cb.like(cb.lower(root.get("receiverFirstName")), "%" + receiverFirstName.toLowerCase() + "%"));
            if (receiverLastName != null) predicates.add(cb.like(cb.lower(root.get("receiverLastName")), "%" + receiverLastName.toLowerCase() + "%"));
            if (receiverPosition != null) predicates.add(cb.like(cb.lower(root.get("receiverPosition")), "%" + receiverPosition.toLowerCase() + "%"));
            if (duration != null) predicates.add(cb.equal(root.get("duration"), duration));
            if (bypassReason != null) predicates.add(cb.like(cb.lower(root.get("bypassReason")), "%" + bypassReason.toLowerCase() + "%"));
            if (notes != null) predicates.add(cb.like(cb.lower(root.get("notes")), "%" + notes.toLowerCase() + "%"));
            if (auditDate != null) predicates.add(cb.equal(root.get("auditDate"), auditDate));
            if (authorizationLimit != null) predicates.add(cb.equal(root.get("authorizationLimit"), authorizationLimit));
            if (authorizationLimitType != null) predicates.add(cb.equal(root.get("authorizationLimitType"), authorizationLimitType));
            if (lastUpdatedBy != null) predicates.add(cb.like(cb.lower(root.get("lastUpdatedBy")), "%" + lastUpdatedBy.toLowerCase() + "%"));
            if (lastUpdatedDate != null) predicates.add(cb.equal(root.get("lastUpdatedDate"), lastUpdatedDate));

            return cb.and(predicates.toArray(Predicate[]::new));
        };
    }
}
