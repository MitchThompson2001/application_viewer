/*
 * Name: Mitchell Thompson
 * File: PatientController.java
 * Project: Data Viewer Application
 */

package com.example.application_viewer.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.application_viewer.components.PatientType;
import com.example.application_viewer.models.Location;
import com.example.application_viewer.models.Patient;
import com.example.application_viewer.models.PatientAddress;
import com.example.application_viewer.models.PatientAttribute;
import com.example.application_viewer.models.PatientAuthAndCert;
import com.example.application_viewer.models.PatientContact;
import com.example.application_viewer.models.PatientDemographic;
import com.example.application_viewer.models.PatientDiagnosisCode;
import com.example.application_viewer.models.PatientDocument;
import com.example.application_viewer.models.PatientInsurance;
import com.example.application_viewer.models.PatientNote;
import com.example.application_viewer.models.PatientOrder;
import com.example.application_viewer.models.PatientTicket;
import com.example.application_viewer.models.PatientTransaction;
import com.example.application_viewer.services.PatientAddressService;
import com.example.application_viewer.services.PatientDemographicService;
import com.example.application_viewer.services.PatientService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/*
 * Controller class that manages http requests relating to the Patient class.
 */
@Controller
public class PatientController {
    
    @Autowired private PatientService patientService;
    @Autowired private PatientAddressService patientAddressService;
    @Autowired private PatientDemographicService patientDemographicService;

    /*
     * Returns the result of a search query for any patient with data that
     * matches the query. If no query is provided, no patients will be returned.
     */
    @GetMapping("/patient_list")
    public String searchPatient(
        @RequestParam(required = false) Long id,
        @RequestParam(required = false) PatientAddress patientAddress,
        @RequestParam(required = false) PatientAttribute patientAttribute,
        @RequestParam(required = false) PatientAuthAndCert patientAuthAndCert,
        @RequestParam(required = false) PatientContact patientContact,
        @RequestParam(required = false) PatientDemographic patientDemographic,
        @RequestParam(required = false) PatientDiagnosisCode patientDiagnosisCode,
        @RequestParam(required = false) PatientDocument patientDocument,
        @RequestParam(required = false) PatientInsurance patientInsurance,
        @RequestParam(required = false) PatientNote patientNote,
        @RequestParam(required = false) PatientOrder patientOrder,
        @RequestParam(required = false) PatientTicket patientTicket,
        @RequestParam(required = false) PatientTransaction patientTransaction,
        @RequestParam(required = false) Location location,
        @RequestParam(required = false) String lastUpdatedBy,
        @RequestParam(required = false) LocalDate lastUpdatedDate,
        @RequestParam(required = false, defaultValue = "id") String sortField,
        @RequestParam(required = false, defaultValue = "asc") String sortDir,
        Model model) {

        List<Patient> patients = patientService.searchAndSortPatients(
            id, 
            patientAddress, 
            patientAttribute, 
            patientAuthAndCert, 
            patientContact, 
            patientDemographic, 
            patientDiagnosisCode, 
            patientDocument, 
            patientInsurance, 
            patientNote, 
            patientOrder, 
            patientTicket,
            patientTransaction,
            location,
            lastUpdatedBy,
            lastUpdatedDate,
            sortField,
            sortDir
        );

        model.addAttribute("allPatList", patients);
        model.addAttribute("id", id);
        model.addAttribute("patientAddress", patientAddress);
        model.addAttribute("patientAttribute", patientAttribute);
        model.addAttribute("patientAuthAndCert", patientAuthAndCert);
        model.addAttribute("patientContact", patientContact);
        model.addAttribute("patientDemographic", patientDemographic);
        model.addAttribute("patientDiagnosisCode", patientDiagnosisCode);
        model.addAttribute("patientDocument", patientDocument);
        model.addAttribute("patientInsurance", patientInsurance);
        model.addAttribute("patientNote", patientNote);
        model.addAttribute("patientOrder", patientOrder);
        model.addAttribute("patientTicket", patientTicket);
        model.addAttribute("patientTransaction", patientTransaction);
        model.addAttribute("location", location);
        model.addAttribute("lastUpdatedBy", lastUpdatedBy);
        model.addAttribute("lastUpdatedDate", lastUpdatedDate);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "patientList";
    }

    @GetMapping("/patient_modal/{patientId}")
    public String getPatientModal(@PathVariable Long patientId, Model model) {

        Patient patient = patientService.findById(patientId);
        System.out.println("Patient id: " + patient.getId());
        PatientAddress patientAddress = patientAddressService.findByPatient(patient);
        System.out.println("Patient address: " + patientAddress.getAddressA());
        PatientDemographic patientDemographic = patientDemographicService.findByPatient(patient);
        System.out.println("Patient first name: " + patientDemographic.getFirstName());

        model.addAttribute("patientId", patientId);
        model.addAttribute("patient", patient);
        model.addAttribute("patientAddress", patientAddress);
        // model.addAttribute("addressTypes", AddressType.values());
        // model.addAttribute("residenceTypes", ResidenceType.values());
        model.addAttribute("patientDemographic", patientDemographic);
        model.addAttribute("patientTypes", PatientType.values());
        // model.addAttribute("sexTypes", SexType.values());
        
        return "fragments/patientModal :: modalContent";
    }

    @PostMapping("update_patient")
    public String updatePatient(@ModelAttribute Patient patient) {
        patientService.updatePatient(patient);
        
        return "redirect:/patient_list";
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
