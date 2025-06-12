package com.example.application_viewer.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
import com.example.application_viewer.services.PatientTicketService;

@Controller
public class PatientTicketController {
    @Autowired private PatientTicketService patientTicketService;

    @GetMapping("/patient_ticket/list")
    public String searchPatientTickets(
        @RequestParam(required = false) Long id,
        @RequestParam(required = false) Patient patient,
        @RequestParam(required = false) PatientOrder patientOrder,
        @RequestParam(required = false) OrderType orderType,
        @RequestParam(required = false) ProductCategory productCategory,
        @RequestParam(required = false) TicketStatus ticketStatus,
        @RequestParam(required = false) String lineItemNumber,
        @RequestParam(required = false) ServiceType serviceType,
        @RequestParam(required = false) BillType billType,
        @RequestParam(required = false) String hcpcsCode,
        @RequestParam(required = false) LocalDate dos,
        @RequestParam(required = false) Integer quantity,
        @RequestParam(required = false) LocalDate nextAction,
        @RequestParam(required = false) Location location,
        @RequestParam(required = false) String manufacturer,
        @RequestParam(required = false) String manufacturerId,
        @RequestParam(required = false) String productName,
        @RequestParam(required = false) String lotNumber,
        @RequestParam(required = false) String serialNumber,
        @RequestParam(required = false) String trackingNumber,
        @RequestParam(required = false) String notes,
        @RequestParam(required = false) String cost,
        @RequestParam(required = false) Boolean billable,
        @RequestParam(required = false) ReasonType reasonType,
        @RequestParam(required = false) String reasonDescription,
        @RequestParam(required = false) LocalDateTime auditDate,
        @RequestParam(required = false) String lastUpdatedBy,
        @RequestParam(required = false) LocalDate lastUpdatedDate,
        @RequestParam(required = false, defaultValue = "id") String sortField,
        @RequestParam(required = false, defaultValue = "asc") String sortDir,
        Model model
        ) {

        List<PatientTicket> tickets = patientTicketService.searchAndSortPatientTickets(
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
            lastUpdatedDate, 
            sortField, 
            sortDir
        );

        model.addAttribute("allPatTickList", tickets);
        model.addAttribute("id", id);
        model.addAttribute("patient", patient);
        model.addAttribute("patientOrder", patientOrder);
        model.addAttribute("orderType", orderType);
        model.addAttribute("productCategory", productCategory);
        model.addAttribute("ticketStatus", ticketStatus);
        model.addAttribute("lineItemNumber", lineItemNumber);
        model.addAttribute("serviceType", serviceType);
        model.addAttribute("billType", billType);
        model.addAttribute("hcpcsCode", hcpcsCode);
        model.addAttribute("dos", dos);
        model.addAttribute("quantity", quantity);
        model.addAttribute("nextAction", nextAction);
        model.addAttribute("location", location);
        model.addAttribute("manufacturer", manufacturer);
        model.addAttribute("manufacturerId", manufacturerId);
        model.addAttribute("productName", productName);
        model.addAttribute("lotNumber", lotNumber);
        model.addAttribute("serialNumber", serialNumber);
        model.addAttribute("trackingNumber", trackingNumber);
        model.addAttribute("notes", notes);
        model.addAttribute("cost", cost);
        model.addAttribute("billable", billable);
        model.addAttribute("reasonType", reasonType);
        model.addAttribute("reasonDescription", reasonDescription);
        model.addAttribute("auditDate", auditDate);
        model.addAttribute("lastUpdatedBy", lastUpdatedBy);
        model.addAttribute("lastUpdatedDate", lastUpdatedDate);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "patientTicketList";
    }
    
    
}
