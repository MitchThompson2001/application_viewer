package com.example.application_viewer.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.application_viewer.components.BillType;
import com.example.application_viewer.components.OrderType;
import com.example.application_viewer.components.ProductCategory;
import com.example.application_viewer.components.ReasonType;
import com.example.application_viewer.components.ServiceType;
import com.example.application_viewer.components.TicketStatus;
import com.example.application_viewer.models.Location;
import com.example.application_viewer.models.Patient;
import com.example.application_viewer.models.PatientOrder;
import com.example.application_viewer.models.PatientTicket;
import com.example.application_viewer.repositories.PatientTicketRepository;
import com.example.application_viewer.specifications.PatientTicketSpecification;

@Service
public class PatientTicketService {
    @Autowired private PatientTicketRepository patientTicketRepository;

    public List<PatientTicket> searchAndSortPatientTickets(
        Long id,
        Patient patient,
        PatientOrder patientOrder,
        OrderType orderType,
        ProductCategory productCategory,
        TicketStatus ticketStatus,
        String lineItemNumber,
        ServiceType serviceType,
        BillType billType,
        String hcpcsCode,
        LocalDate dos,
        Integer quantity,
        LocalDate nextAction,
        Location location,
        String manufacturer,
        String manufacturerId,
        String productName,
        String lotNumber,
        String serialNumber,
        String trackingNumber,
        String notes,
        String cost,
        Boolean billable,
        ReasonType reasonType,
        String reasonDescription,
        LocalDateTime auditDate,
        String lastUpdatedBy,
        LocalDate lastUpdatedDate,
        String sortField,
        String sortDir
    ) {
        Specification<PatientTicket> spec = PatientTicketSpecification.filterBy(
            id, 
            patient, 
            patientOrder, 
            orderType, 
            productCategory, 
            ticketStatus, 
            lineItemNumber, 
            serviceType, 
            billType, 
            hcpcsCode, 
            dos, 
            quantity, 
            nextAction, 
            location, 
            manufacturer, 
            manufacturerId, 
            productName, 
            lotNumber, 
            serialNumber, 
            trackingNumber, 
            notes, 
            cost, 
            billable, 
            reasonType, 
            reasonDescription, 
            auditDate, 
            lastUpdatedBy, 
            lastUpdatedDate
        );

        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortField);

        return patientTicketRepository.findAll(spec, sort);
    }
}
