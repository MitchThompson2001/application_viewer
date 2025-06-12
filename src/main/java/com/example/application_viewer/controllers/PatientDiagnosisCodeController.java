package com.example.application_viewer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.application_viewer.models.Patient;
import com.example.application_viewer.models.PatientDiagnosisCode;
import com.example.application_viewer.services.PatientDiagnosisCodeService;

@Controller
public class PatientDiagnosisCodeController {
    @Autowired private PatientDiagnosisCodeService patientDiagnosisCodeService;

    @GetMapping("/patient_diagnosis_code_list")
    public String searchPatientDiagnosisCodes(
        @RequestParam(required = false) Long id,
        @RequestParam(required = false) Patient patient,
        @RequestParam(required = false) String code,
        @RequestParam(required = false) String description,
        @RequestParam(required = false, defaultValue = "id") String sortField,
        @RequestParam(required = false, defaultValue = "asc") String sortDir,
        Model model) {

        List<PatientDiagnosisCode> diagnosisCodes = patientDiagnosisCodeService.searchPatientDiagnosisCodes(
            id, 
            patient, 
            code, 
            description, 
            sortField, 
            sortDir);

        model.addAttribute("allPatDiagCodeList", diagnosisCodes);
        model.addAttribute("id", id);
        model.addAttribute("patient", patient);
        model.addAttribute("code", code);
        model.addAttribute("description", description);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "patientDiagnosisCodeList";
    }
    
    
}
