/*
 * Name: Mitchell Thompson
 * File: PatientController.java
 * Project: Data Viewer Application
 */

package com.example.application_viewer.controllers;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.application_viewer.models.Patient;
import com.example.application_viewer.models.PatientDemographic;
import com.example.application_viewer.services.PatientService;

/*
 * Controller class that manages http requests relating to the Patient class.
 */
@Controller
public class PatientController {
    
    @Autowired private PatientService patientService;

    /*
     * Returns the result of a search query for any patient with data that
     * matches the query. If no query is provided, no patients will be returned.
     */
    @GetMapping("/patient_list")
    public String searchPatient(
        @RequestParam(required = false) Long id,
        @RequestParam(required = false) PatientDemographic patientDemographic,
        @RequestParam(required = false, defaultValue = "id") String sortField,
        @RequestParam(required = false, defaultValue = "asc") String sortDir,
        Model model) {

        List<Patient> patients = patientService.searchAndSortPatients(
            id,
            patientDemographic,
            sortField, 
            sortDir);

        model.addAttribute("allPatList", patients);
        model.addAttribute("id", id);
        model.addAttribute("patientDemographic", patientDemographic);

        return "patientList";
    }


    // Methods below not included due to read-only implementation for patient data
    // @GetMapping("/add_patient")
    // public String addNewPatient(Model model) {
    //     Patient patient = new Patient();
    //     model.addAttribute("patient", patient);

    //     return "newPatient";
    // }

    // @PostMapping("/save_patient")
    // public String savePatient(@ModelAttribute("patient") Patient patient) {
    //     patientService.save(patient);
    //     return "redirect:/";
    // }

    // @GetMapping("show_update_form/{id}")
    // public String updateForm(@PathVariable(value = "id") long id, Model model) {
    //     Patient patient = patientService.getByID(id);
    //     model.addAttribute("patient", patient);
    //     return "update";
    // }

    // @GetMapping("delete_patient/{id}")
    // public String deleteByID(@PathVariable(value = "id") long id) {
    //     patientService.deleteByID(id);
    //     return "redirect:/";
    // }

}
