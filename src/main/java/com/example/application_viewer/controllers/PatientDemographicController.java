package com.example.application_viewer.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.application_viewer.components.PatientType;
import com.example.application_viewer.components.SexType;
import com.example.application_viewer.models.Patient;
import com.example.application_viewer.models.PatientDemographic;
import com.example.application_viewer.services.PatientDemographicService;

@Controller
public class PatientDemographicController {
    @Autowired private PatientDemographicService patientDemographicService;

    /*
     * Returns the result of a search query for any patient demographic with data that
     * matches the query. If no query is provided, no patient demographics will be returned.
     */
    @GetMapping("/patient_demographic_list")
    public String searchPatientDemographics(
        @RequestParam(required = false) Long id,
        @RequestParam(required = false) Patient patient,
        @RequestParam(required = false) PatientType patientType,
        @RequestParam(required = false) Boolean active,
        @RequestParam(required = false) String firstName,
        @RequestParam(required = false) String lastName,
        @RequestParam(required = false) String origin,
        @RequestParam(required = false) String emailA,
        @RequestParam(required = false) String emailB,
        @RequestParam(required = false) LocalDate dob,
        @RequestParam(required = false) String phoneA,
        @RequestParam(required = false) String phoneB,
        @RequestParam(required = false) String ssnLastFour,
        @RequestParam(required = false) String height,
        @RequestParam(required = false) String weight,
        @RequestParam(required = false) SexType sex,
        @RequestParam(required = false) String notes,
        @RequestParam(required = false) String homeCompanyNum,
        @RequestParam(required = false) String homeCompanyLoc,
        @RequestParam(required = false) LocalDate lastUpdated,
        @RequestParam(required = false, defaultValue = "id") String sortField,
        @RequestParam(required = false, defaultValue = "asc") String sortDir,
        Model model) {

        List<PatientDemographic> patientDemographics = patientDemographicService.searchAndSortPatientDemographics(
            id,
            patient,
            patientType,
            active,
            firstName, 
            lastName,
            origin, 
            emailA,
            emailB, 
            dob, 
            phoneA,
            phoneB,
            ssnLastFour,
            height,
            weight,
            sex,
            notes,
            homeCompanyNum,
            homeCompanyLoc,
            lastUpdated, 
            sortField, 
            sortDir);

        model.addAttribute("allPatDemList", patientDemographics);
        model.addAttribute("id", id);
        model.addAttribute("patient", patient);
        model.addAttribute("patientType", patientType);
        model.addAttribute("active", active);
        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("origin", origin);
        model.addAttribute("emailA", emailA);
        model.addAttribute("emailB", emailB);
        model.addAttribute("dob", dob);
        model.addAttribute("phoneA", phoneA);
        model.addAttribute("phoneB", phoneB);
        model.addAttribute("ssnLastFour", ssnLastFour);
        model.addAttribute("height", height);
        model.addAttribute("weight", weight);
        model.addAttribute("sex", sex);
        model.addAttribute("notes", notes);
        model.addAttribute("homeCompanyNum", homeCompanyNum);
        model.addAttribute("homeCompanyLoc", homeCompanyLoc);
        model.addAttribute("lastUpdated", lastUpdated);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "patientDemographicList";
    }
}
