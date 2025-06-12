package com.example.application_viewer.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.application_viewer.components.AuthorizationLimitType;
import com.example.application_viewer.components.AuthorizationType;
import com.example.application_viewer.components.BillType;
import com.example.application_viewer.components.OrderType;
import com.example.application_viewer.models.Patient;
import com.example.application_viewer.models.PatientAuthAndCert;
import com.example.application_viewer.models.PatientDocument;
import com.example.application_viewer.models.PatientOrder;
import com.example.application_viewer.repositories.PatientAuthAndCertRepository;
import com.example.application_viewer.specifications.PatientAuthAndCertSpecification;

@Service
public class PatientAuthAndCertService {
    @Autowired private PatientAuthAndCertRepository patientAuthAndCertRepository;

    public List<PatientAuthAndCert> searchAndSortPatientAuthsAndCerts (
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
        LocalDate lastUpdatedDate,
        String sortField,
        String sortDir) {

        Specification<PatientAuthAndCert> spec = PatientAuthAndCertSpecification.filterBy(
            id, 
            patient, 
            patientOrder, 
            orderType, 
            authCertNumber, 
            authCertType, 
            planCode, 
            patientDocument, 
            billType, 
            hcpcsCode, 
            ticketDos, 
            description, 
            startDate, 
            dueDate, 
            receiverFirstName, 
            receiverLastName, 
            receiverPosition, 
            duration, 
            bypassReason, 
            notes, 
            auditDate, 
            authorizationLimit, 
            authorizationLimitType, 
            lastUpdatedBy, 
            lastUpdatedDate);

        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortField);

        return patientAuthAndCertRepository.findAll(spec, sort);
    }
}
