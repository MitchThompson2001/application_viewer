package com.example.application_viewer.specifications;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.example.application_viewer.models.Patient;
import com.example.application_viewer.models.PatientAddress;
import com.example.application_viewer.models.PatientAttribute;
import com.example.application_viewer.models.PatientAuthAndCert;
import com.example.application_viewer.models.PatientContact;
import com.example.application_viewer.models.PatientDemographic;
import com.example.application_viewer.models.PatientDiagnosisCode;
import com.example.application_viewer.models.PatientDocument;
import com.example.application_viewer.models.PatientInsurance;
import com.example.application_viewer.models.PatientNote;
import com.example.application_viewer.models.PatientOrder;
import com.example.application_viewer.models.PatientTicket;
import com.example.application_viewer.models.PatientTransaction;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;

public class PatientSpecification {
    
    public static Specification<Patient> filterBy(
        Long id,
        PatientAddress patientAddress,
        PatientAttribute patientAttribute,
        PatientAuthAndCert patientAuthAndCert,
        PatientContact patientContact,
        PatientDemographic patientDemographic,
        PatientDiagnosisCode patientDiagnosisCode,
        PatientDocument patientDocument,
        PatientInsurance patientInsurance,
        PatientNote patientNote,
        PatientOrder patientOrder,
        PatientTicket patientTicket,
        PatientTransaction patientTransaction
    ) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (id != null) predicates.add(cb.equal(root.get("id"), id));
            if (patientAddress != null) predicates.add(cb.equal(root.get("patientAddress"), patientAddress));
            if (patientAuthAndCert != null) {
                Join<PatientOrder, PatientAuthAndCert> ticketJoin = root.join("patient_auths_and_certs", JoinType.INNER);
                predicates.add(cb.equal(ticketJoin, patientAuthAndCert));
            }
            if (patientContact != null) {
                Join<PatientOrder, PatientAuthAndCert> ticketJoin = root.join("patient_contacts", JoinType.INNER);
                predicates.add(cb.equal(ticketJoin, patientContact));
            }
            if (patientDemographic != null) predicates.add(cb.equal(root.get("patientDemographic"), patientDemographic));
            if (patientDiagnosisCode != null) {
                Join<PatientOrder, PatientAuthAndCert> ticketJoin = root.join("patient_diagnosis_codes", JoinType.INNER);
                predicates.add(cb.equal(ticketJoin, patientDiagnosisCode));
            }
            if (patientDocument != null) {
                Join<PatientOrder, PatientAuthAndCert> ticketJoin = root.join("patient_documents", JoinType.INNER);
                predicates.add(cb.equal(ticketJoin, patientDocument));
            }
            if (patientInsurance != null) predicates.add(cb.equal(root.get("patientInsurance"), patientInsurance));
            if (patientNote != null) {
                Join<PatientOrder, PatientAuthAndCert> ticketJoin = root.join("patient_notes", JoinType.INNER);
                predicates.add(cb.equal(ticketJoin, patientNote));
            }
            if (patientOrder != null) {
                Join<PatientOrder, PatientAuthAndCert> ticketJoin = root.join("patient_orders", JoinType.INNER);
                predicates.add(cb.equal(ticketJoin, patientOrder));
            }
            if (patientTicket != null) {
                Join<PatientOrder, PatientAuthAndCert> ticketJoin = root.join("patient_tickets", JoinType.INNER);
                predicates.add(cb.equal(ticketJoin, patientTicket));
            }
            if (patientTransaction != null) {
                Join<PatientOrder, PatientAuthAndCert> ticketJoin = root.join("patient_transactions", JoinType.INNER);
                predicates.add(cb.equal(ticketJoin, patientTransaction));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
