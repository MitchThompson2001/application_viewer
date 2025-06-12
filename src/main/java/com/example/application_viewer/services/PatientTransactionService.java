package com.example.application_viewer.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.application_viewer.components.BillType;
import com.example.application_viewer.components.TransactionSubType;
import com.example.application_viewer.components.TransactionType;
import com.example.application_viewer.models.Location;
import com.example.application_viewer.models.Patient;
import com.example.application_viewer.models.PatientOrder;
import com.example.application_viewer.models.PatientTransaction;
import com.example.application_viewer.repositories.PatientTransactionRepository;
import com.example.application_viewer.specifications.PatientTransactionSpecification;

@Service
public class PatientTransactionService {
    @Autowired private PatientTransactionRepository patientTransactionRepository;

    public List<PatientTransaction> searchAndSortPatientTransactions(
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
        LocalDateTime auditUpdateDate,
        String sortField,
        String sortDir
    ) {
        Specification<PatientTransaction> spec = PatientTransactionSpecification.filterBy(
            id, 
            location, 
            patient, 
            patientOrder, 
            hcpc, 
            billType, 
            cost, 
            transactionType, 
            transactionSubType, 
            transactionDate, 
            insuranceId, 
            insuranceName, 
            claimNumber, 
            notes, 
            auditDate, 
            auditUpdateDate
        );

        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortField);

        return patientTransactionRepository.findAll(spec, sort);
    }
    
}
