package com.example.application_viewer.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.application_viewer.components.NoteType;
import com.example.application_viewer.models.Patient;
import com.example.application_viewer.models.PatientNote;
import com.example.application_viewer.repositories.PatientNoteRepository;
import com.example.application_viewer.specifications.PatientNoteSpecification;

@Service
public class PatientNoteService {
    @Autowired private PatientNoteRepository patientNoteRepository;

    public List<PatientNote> searchAndSortPatientNotes(
        Long id,
        Patient patient,
        LocalDate loggedDate,
        NoteType noteType,
        String note,
        LocalDateTime auditDate,
        String lastUpdatedBy,
        LocalDate lastUpdatedDate,
        String sortField,
        String sortDir
    ) {
        Specification<PatientNote> spec = PatientNoteSpecification.filterBy(
            id, 
            patient, 
            loggedDate, 
            noteType, 
            note, 
            auditDate, 
            lastUpdatedBy, 
            lastUpdatedDate
        );

        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortField);

        return patientNoteRepository.findAll(spec, sort);
    }
}
