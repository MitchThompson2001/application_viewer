package com.example.application_viewer.specifications;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.example.application_viewer.components.OrderType;
import com.example.application_viewer.models.Patient;
import com.example.application_viewer.models.PatientOrder;
import com.example.application_viewer.models.PatientTicket;
import com.example.application_viewer.models.PatientTransaction;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;

public class PatientOrderSpecification {

    public static Specification<PatientOrder> filterBy(
        Long id,
        Patient patient,
        OrderType orderType,
        LocalDate doctorOrderDate,
        String doctorFirstName,
        String doctorLastName,
        String doctorNpi,
        LocalDate orderStartDate,
        LocalDate orderDueDate,
        LocalDate orderCompletedDate,
        String instructions,
        String diagnosticCodes,
        Boolean qualified,
        String referralSource,
        String referralSourceNpi,
        LocalDate referralDate,
        String lastUpdatedBy,
        LocalDate lastUpdatedDate,
        PatientTicket patientTicket,
        PatientTransaction patientTransaction
    ) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (id != null) predicates.add(cb.equal(root.get("id"), id));
            if (patient != null) predicates.add(cb.equal(root.get("patient"), patient));
            if (orderType != null) predicates.add(cb.equal(root.get("orderType"), orderType));
            if (doctorOrderDate != null) predicates.add(cb.equal(root.get("doctorOrderDate"), doctorOrderDate));
            if (doctorFirstName != null) predicates.add(cb.like(cb.lower(root.get("doctorFirstName")), "%" + doctorFirstName.toLowerCase() + "%"));
            if (doctorLastName != null) predicates.add(cb.like(cb.lower(root.get("doctorLastName")), "%" + doctorLastName.toLowerCase() + "%"));
            if (doctorNpi != null) predicates.add(cb.equal(root.get("doctorNpi"), doctorNpi));
            if (orderStartDate != null) predicates.add(cb.equal(root.get("orderStartDate"), orderStartDate));
            if (orderDueDate != null) predicates.add(cb.equal(root.get("orderDueDate"), orderDueDate));
            if (orderCompletedDate != null) predicates.add(cb.equal(root.get("orderCompletedDate"), orderCompletedDate));
            if (instructions != null) predicates.add(cb.like(cb.lower(root.get("instructions")), "%" + instructions.toLowerCase() + "%"));
            if (diagnosticCodes != null) predicates.add(cb.equal(root.get("diagnosticCodes"), diagnosticCodes));
            if (qualified != null) predicates.add(cb.equal(root.get("qualified"), qualified));
            if (referralSource != null) predicates.add(cb.like(cb.lower(root.get("referralSource")), "%" + referralSource.toLowerCase() + "%"));
            if (referralSourceNpi != null) predicates.add(cb.equal(root.get("referralSourceNpi"), referralSourceNpi));
            if (referralDate != null) predicates.add(cb.equal(root.get("referralDate"), referralDate));
            if (lastUpdatedBy != null) predicates.add(cb.like(cb.lower(root.get("lastUpdatedBy")), "%" + lastUpdatedBy.toLowerCase() + "%"));
            if (lastUpdatedDate != null) predicates.add(cb.equal(root.get("lastUpdatedDate"), lastUpdatedDate));
            if (patientTicket != null) {
                Join<PatientOrder, PatientTicket> ticketJoin = root.join("patient_tickets", JoinType.INNER);
                predicates.add(cb.equal(ticketJoin, patientTicket));
            }
            if (patientTransaction != null) {
                Join<PatientOrder, PatientTransaction> ticketJoin = root.join("patient_transactions", JoinType.INNER);
                predicates.add(cb.equal(ticketJoin, patientTransaction));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
    
}
