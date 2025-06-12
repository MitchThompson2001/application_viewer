package com.example.application_viewer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.application_viewer.components.EmailType;
import com.example.application_viewer.components.PhoneType;
import com.example.application_viewer.components.RelationshipType;
import com.example.application_viewer.models.Patient;
import com.example.application_viewer.models.PatientContact;
import com.example.application_viewer.services.PatientContactService;

@Controller
public class PatientContactController {
    @Autowired private PatientContactService patientContactService;

    @GetMapping("patient_contact_list")
    public String getPatientContacts(
        @RequestParam(required = false) Long id,
        @RequestParam(required = false) Patient patient,
        @RequestParam(required = false) String firstName,
        @RequestParam(required = false) String lastName,
        @RequestParam(required = false) RelationshipType relationshipType,
        @RequestParam(required = false) String phone,
        @RequestParam(required = false) PhoneType phoneType,
        @RequestParam(required = false) String email,
        @RequestParam(required = false) EmailType emailType,
        @RequestParam(required = false) Boolean emergencyContact,
        @RequestParam(required = false) String notes,
        @RequestParam(required = false) String sortField,
        @RequestParam(required = false) String sortDir,
        Model model) {
        
        List<PatientContact> contacts = patientContactService.searchAndPatientContacts(
            id, 
            patient, 
            firstName, 
            lastName, 
            relationshipType, 
            phone, 
            phoneType, 
            email, 
            emailType, 
            emergencyContact, 
            notes, 
            sortField, 
            sortDir);

        model.addAttribute("allPatConList", contacts);
        model.addAttribute("id", id);
        model.addAttribute("patient", patient);
        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("relationshipType", relationshipType);
        model.addAttribute("phone", phone);
        model.addAttribute("phoneType", phoneType);
        model.addAttribute("email", email);
        model.addAttribute("emailType", emailType);
        model.addAttribute("emergencyContact", emergencyContact);
        model.addAttribute("notes", notes);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "patientContactList";
    }
    
}
