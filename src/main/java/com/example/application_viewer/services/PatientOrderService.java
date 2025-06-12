package com.example.application_viewer.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.application_viewer.components.OrderType;
import com.example.application_viewer.models.Patient;
import com.example.application_viewer.models.PatientOrder;
import com.example.application_viewer.models.PatientTicket;
import com.example.application_viewer.models.PatientTransaction;
import com.example.application_viewer.repositories.PatientOrderRepository;
import com.example.application_viewer.specifications.PatientOrderSpecification;

@Service
public class PatientOrderService {

    @Autowired private PatientOrderRepository patientOrderRepository;

    public List<PatientOrder> searchAndSortPatientOrders(
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
        PatientTransaction patientTransaction,
        String sortField,
        String sortDir
    ) {
        Specification<PatientOrder> spec = PatientOrderSpecification.filterBy(
            id, 
            patient, 
            orderType, 
            doctorOrderDate, 
            doctorFirstName, 
            doctorLastName, 
            doctorNpi, 
            orderStartDate, 
            orderDueDate, 
            orderCompletedDate, 
            instructions, 
            diagnosticCodes, 
            qualified, 
            referralSource, 
            referralSourceNpi, 
            referralDate, 
            lastUpdatedBy, 
            lastUpdatedDate, 
            patientTicket, 
            patientTransaction
        );

        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortField);

        return patientOrderRepository.findAll(spec, sort);
    }
    
}
