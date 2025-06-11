package com.example.application_viewer.controllers;

import java.util.List;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.application_viewer.components.AddressType;
import com.example.application_viewer.components.ResidenceType;
import com.example.application_viewer.models.Patient;
import com.example.application_viewer.models.PatientAttribute;
import com.example.application_viewer.services.PatientAttributeService;

@Controller
public class PatientAttributeController {

    @Autowired private PatientAttributeService patientAttributeService;

    @GetMapping("patient_attribute_list")
    public String searchPatientAttributes(
        @RequestParam(required = false) Long id,
        @RequestParam(required = false) Patient patient,
        @RequestParam(required = false) Boolean active,
        @RequestParam(required = false) LocalDate effectiveDate,
        @RequestParam(required = false) String attribute,
        @RequestParam(required = false, defaultValue = "id") String sortField,
        @RequestParam(required = false, defaultValue = "asc") String sortDir,
        Model model) {

        List<PatientAttribute> attributes = patientAttributeService.searchAndSortPatientAttributes(
            id,
            patient,
            active,
            effectiveDate,
            attribute,
            sortField,
            sortDir);

        model.addAttribute("allPatAttrList", attributes);
        model.addAttribute("id", id);
        model.addAttribute("active", active);
        model.addAttribute("effectiveDate", effectiveDate);
        model.addAttribute("attribute", attribute);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "patientAttributeList";
    }
    
}
