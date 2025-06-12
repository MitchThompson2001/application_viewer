package com.example.application_viewer.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.application_viewer.components.NoteType;
import com.example.application_viewer.models.Patient;
import com.example.application_viewer.models.PatientNote;
import com.example.application_viewer.services.PatientNoteService;

@Controller
public class PatientNoteController {
    @Autowired private PatientNoteService patientNoteService;

    @GetMapping("/patient_note_list")
    public String getPatientNotes(
        @RequestParam(required = false) Long id,
        @RequestParam(required = false) Patient patient,
        @RequestParam(required = false) LocalDate loggedDate,
        @RequestParam(required = false) NoteType noteType,
        @RequestParam(required = false) String note,
        @RequestParam(required = false) LocalDateTime auditDate,
        @RequestParam(required = false) String lastUpdatedBy,
        @RequestParam(required = false) LocalDate lastUpdatedDate,
        @RequestParam(required = false, defaultValue = "id") String sortField,
        @RequestParam(required = false, defaultValue = "asc") String sortDir,
        Model model
    ) {
        List<PatientNote> patientNotes = patientNoteService.searchAndSortPatientNotes(
            id, 
            patient, 
            loggedDate, 
            noteType, 
            note, 
            auditDate, 
            lastUpdatedBy, 
            lastUpdatedDate, 
            sortField, 
            sortDir
        );

        model.addAttribute("allPatNotList", patientNotes);
        model.addAttribute("id", id);
        model.addAttribute("patient", patient);
        model.addAttribute("loggedDate", loggedDate);
        model.addAttribute("noteType", noteType);
        model.addAttribute("note", note);
        model.addAttribute("auditDate", auditDate);
        model.addAttribute("lastUpdatedBy", lastUpdatedBy);
        model.addAttribute("lastUpdatedDate", lastUpdatedDate);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return new String();
    }
    
}
