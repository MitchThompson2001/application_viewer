package com.example.application_viewer.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.application_viewer.components.OrderType;
import com.example.application_viewer.models.Patient;
import com.example.application_viewer.models.PatientOrder;
import com.example.application_viewer.models.PatientTicket;
import com.example.application_viewer.models.PatientTransaction;
import com.example.application_viewer.services.PatientOrderService;

@Controller
public class PatientOrderController {
    @Autowired private PatientOrderService patientOrderService;

    @GetMapping("/patient_order_list")
    public String searchPatientOrders(
        @RequestParam(required = false) Long id,
        @RequestParam(required = false) Patient patient,
        @RequestParam(required = false) OrderType orderType,
        @RequestParam(required = false) LocalDate doctorOrderDate,
        @RequestParam(required = false) String doctorFirstName,
        @RequestParam(required = false) String doctorLastName,
        @RequestParam(required = false) String doctorNpi,
        @RequestParam(required = false) LocalDate orderStartDate,
        @RequestParam(required = false) LocalDate orderDueDate,
        @RequestParam(required = false) LocalDate orderCompletedDate,
        @RequestParam(required = false) String instructions,
        @RequestParam(required = false) String diagnosticCodes,
        @RequestParam(required = false) Boolean qualified,
        @RequestParam(required = false) String referralSource,
        @RequestParam(required = false) String referralSourceNpi,
        @RequestParam(required = false) LocalDate referralDate,
        @RequestParam(required = false) String lastUpdatedBy,
        @RequestParam(required = false) LocalDate lastUpdatedDate,
        @RequestParam(required = false) Long patientTicketId,
        @RequestParam(required = false) Long patientTransactionId,
        @RequestParam(required = false) Set<PatientTicket> tickets,
        @RequestParam(required = false) Set<PatientTransaction> transactions,
        @RequestParam(required = false, defaultValue = "id") String sortField,
        @RequestParam(required = false, defaultValue = "asc") String sortDir,
        Model model
    ) {
        List<PatientOrder> orders = patientOrderService.searchAndSortPatientOrders(
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
            patientTicketId, 
            patientTransactionId, 
            tickets, 
            transactions, 
            sortField, 
            sortDir
        );

        model.addAttribute("allPatOrdList", orders);
        model.addAttribute("id", id);
        model.addAttribute("patient", patient);
        model.addAttribute("orderType", orderType);
        model.addAttribute("doctorOrderDate", doctorOrderDate);
        model.addAttribute("doctorFirstName", doctorFirstName);
        model.addAttribute("doctorLastName", doctorLastName);
        model.addAttribute("doctorNpi", doctorNpi);
        model.addAttribute("orderStartDate", orderStartDate);
        model.addAttribute("orderDueDate", orderDueDate);
        model.addAttribute("orderCompletedDate", orderCompletedDate);
        model.addAttribute("instructions", instructions);
        model.addAttribute("diagnosticCodes", diagnosticCodes);
        model.addAttribute("qualified", qualified);
        model.addAttribute("referralSource", referralSource);
        model.addAttribute("referralSourceNpi", referralSourceNpi);
        model.addAttribute("referralDate", referralDate);
        model.addAttribute("lastUpdatedBy", lastUpdatedBy);
        model.addAttribute("lastUpdatedDate", lastUpdatedDate);
        model.addAttribute("patientTicketId", patientTicketId);
        model.addAttribute("patientTransactionId", patientTransactionId);
        model.addAttribute("tickets", tickets);
        model.addAttribute("transactions", transactions);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "patientOrderList";
    }
    
    
}
