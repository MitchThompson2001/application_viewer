package com.example.application_viewer.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.application_viewer.components.AuthorizationLimitType;
import com.example.application_viewer.components.AuthorizationType;
import com.example.application_viewer.components.BillType;
import com.example.application_viewer.components.OrderType;
import com.example.application_viewer.models.Patient;
import com.example.application_viewer.models.PatientAuthAndCert;
import com.example.application_viewer.models.PatientDocument;
import com.example.application_viewer.models.PatientOrder;
import com.example.application_viewer.services.PatientAuthAndCertService;

@Controller
public class PatientAuthAndCertController {
    @Autowired private PatientAuthAndCertService patientAuthAndCertService;

    @GetMapping("patient_auth_and_cert_list")
    public String searchPatientAuthAndCert(
        @RequestParam(required = false)Long id,
        @RequestParam(required = false)Patient patient,
        @RequestParam(required = false)PatientOrder patientOrder,
        @RequestParam(required = false)OrderType orderType,
        @RequestParam(required = false)String authCertNumber,
        @RequestParam(required = false)AuthorizationType authCertType,
        @RequestParam(required = false)String planCode,
        @RequestParam(required = false)PatientDocument patientDocument,
        @RequestParam(required = false)BillType billType,
        @RequestParam(required = false)String hcpcsCode,
        @RequestParam(required = false)LocalDate ticketDos,
        @RequestParam(required = false)String description,
        @RequestParam(required = false)LocalDate startDate,
        @RequestParam(required = false)LocalDate dueDate,
        @RequestParam(required = false)String receiverFirstName,
        @RequestParam(required = false)String receiverLastName,
        @RequestParam(required = false)String receiverPosition,
        @RequestParam(required = false)Integer duration,
        @RequestParam(required = false)String bypassReason,
        @RequestParam(required = false)String notes,
        @RequestParam(required = false)LocalDateTime auditDate,
        @RequestParam(required = false)String authorizationLimit,
        @RequestParam(required = false)AuthorizationLimitType authorizationLimitType,
        @RequestParam(required = false)String lastUpdatedBy,
        @RequestParam(required = false)LocalDate lastUpdatedDate,
        @RequestParam(required = false, defaultValue = "id") String sortField,
        @RequestParam(required = false, defaultValue = "asc") String sortDir,
        Model model) {

        List<PatientAuthAndCert> authsAndCerts = patientAuthAndCertService.searchAndSortPatientAuthsAndCerts(
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
            lastUpdatedDate,
            sortField,
            sortDir);

            model.addAttribute("allPatAuthCert", authsAndCerts);
            model.addAttribute("id", id);
            model.addAttribute("patient", patient);
            model.addAttribute("patientOrder", patientOrder);
            model.addAttribute("orderType", orderType);
            model.addAttribute("authCertNumber", authCertNumber);
            model.addAttribute("authCertType", authCertType);
            model.addAttribute("planCode", planCode);
            model.addAttribute("patientDocument", patientDocument);
            model.addAttribute("billType", billType);
            model.addAttribute("hcpcsCode", hcpcsCode);
            model.addAttribute("ticketDos", ticketDos);
            model.addAttribute("description", description);
            model.addAttribute("startDate", startDate);
            model.addAttribute("dueDate", dueDate);
            model.addAttribute("receiverFirstName", receiverFirstName);
            model.addAttribute("receiverLastName", receiverLastName);
            model.addAttribute("receiverPosition", receiverPosition);
            model.addAttribute("duration", duration);
            model.addAttribute("bypassReason", bypassReason);
            model.addAttribute("notes", notes);
            model.addAttribute("auditDate", auditDate);
            model.addAttribute("authorizationLimit", authorizationLimit);
            model.addAttribute("authorizationLimitType", authorizationLimitType);
            model.addAttribute("lastUpdatedBy", lastUpdatedBy);
            model.addAttribute("lastUpdatedDate", lastUpdatedDate);
            model.addAttribute("sortField", sortField);
            model.addAttribute("sortDir", sortDir);
            model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "patientAuthAndCertList";
    }
    
}
