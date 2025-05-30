package com.example.application_viewer.controllers;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.application_viewer.models.Patient;
import com.example.application_viewer.services.PatientService;

/*
 * Controller class that manages Patients defined in the model pkg.
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
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String dob,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false, defaultValue = "firstName") String sortField,
            @RequestParam(required = false, defaultValue = "asc") String sortDir,
            Model model) {
        
        boolean hasSearchInput = Stream.of(firstName, lastName, email, dob, phone)
                                .anyMatch(val -> val != null && !val.trim().isEmpty());

        List<Patient> patients;
        if (hasSearchInput) {
            patients = patientService.searchAndSortPatients(firstName, lastName, email, dob, phone, sortField, sortDir);
        } else {
            // Show all patients sorted, even if no filters
            patients = patientService.searchAndSortPatients(null, null, null, null, null, sortField, sortDir);
        }

        model.addAttribute("allPatList", patients);
        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("email", email);
        model.addAttribute("dob", dob);
        model.addAttribute("phone", phone);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

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
