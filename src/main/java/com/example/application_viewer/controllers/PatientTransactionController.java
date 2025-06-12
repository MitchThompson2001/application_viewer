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
import com.example.application_viewer.components.TransactionSubType;
import com.example.application_viewer.components.TransactionType;
import com.example.application_viewer.models.Location;
import com.example.application_viewer.models.Patient;
import com.example.application_viewer.models.PatientOrder;
import com.example.application_viewer.models.PatientTransaction;
import com.example.application_viewer.services.PatientTransactionService;

@Controller
public class PatientTransactionController {
    @Autowired private PatientTransactionService patientTransactionService;

    @GetMapping("/patient_transaction_list")
    public String searchPatientTransactions(
        @RequestParam(required = false) Long id,
        @RequestParam(required = false) Location location,
        @RequestParam(required = false) Patient patient,
        @RequestParam(required = false) PatientOrder patientOrder,
        @RequestParam(required = false) String hcpc,
        @RequestParam(required = false) BillType billType,
        @RequestParam(required = false) Double cost,
        @RequestParam(required = false) TransactionType transactionType,
        @RequestParam(required = false) TransactionSubType transactionSubType,
        @RequestParam(required = false) LocalDate transactionDate,
        @RequestParam(required = false) String insuranceId,
        @RequestParam(required = false) String insuranceName,
        @RequestParam(required = false) String claimNumber,
        @RequestParam(required = false) String notes,
        @RequestParam(required = false) LocalDateTime auditDate,
        @RequestParam(required = false) LocalDateTime auditUpdateDate,
        @RequestParam(required = false, defaultValue = "id") String sortField,
        @RequestParam(required = false, defaultValue = "asc") String sortDir,
        Model model
    ) {
        List<PatientTransaction> transactions = patientTransactionService.searchAndSortPatientTransactions(
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
            auditUpdateDate, 
            sortField, 
            sortDir
        );

        model.addAttribute("allPatTransList", transactions);
        model.addAttribute("id", id);
        model.addAttribute("location", location);
        model.addAttribute("patient", patient);
        model.addAttribute("patientOrder", patientOrder);
        model.addAttribute("hcpc", hcpc);
        model.addAttribute("billType", billType);
        model.addAttribute("cost", cost);
        model.addAttribute("transactionType", transactionType);
        model.addAttribute("transactionSubType", transactionSubType);
        model.addAttribute("transactionDate", transactionDate);
        model.addAttribute("insuranceId", insuranceId);
        model.addAttribute("insuranceName", insuranceName);
        model.addAttribute("claimNumber", claimNumber);
        model.addAttribute("notes", notes);
        model.addAttribute("auditDate", auditDate);
        model.addAttribute("auditUpdateDate", auditUpdateDate);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "patientTransactionList";
    }
    
    
}
