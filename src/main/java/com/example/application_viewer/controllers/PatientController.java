package com.example.application_viewer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.application_viewer.models.Patient;
import com.example.application_viewer.services.PatientService;

@Controller
public class PatientController {
    
    @Autowired
    private PatientService patientService;

    @GetMapping("/patient_list")
    public String viewHomePage(Model model) {
        model.addAttribute("allPatList", patientService.getAllPatients());
        return "patientList";
    }

    @GetMapping("/add_patient")
    public String addNewPatient(Model model) {
        Patient patient = new Patient();
        model.addAttribute("patient", patient);

        return "newPatient";
    }

    @PostMapping("/save_patient")
    public String savePatient(@ModelAttribute("patient") Patient patient) {
        patientService.save(patient);
        return "redirect:/";
    }

    @GetMapping("show_update_form/{id}")
    public String updateForm(@PathVariable(value = "id") long id, Model model) {
        Patient patient = patientService.getByID(id);
        model.addAttribute("patient", patient);
        return "update";
    }

    @GetMapping("delete_patient/{id}")
    public String deleteByID(@PathVariable(value = "id") long id) {
        patientService.deleteByID(id);
        return "redirect:/";
    }

}
