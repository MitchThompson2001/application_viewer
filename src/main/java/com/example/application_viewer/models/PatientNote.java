package com.example.application_viewer.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.application_viewer.components.NoteType;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "patient_notes")
public class PatientNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MM-dd-yyyy")
    @Column(name = "logged_date")
    private LocalDate loggedDate;
    
    @Column(name = "note_type")
    private NoteType noteType;

    @Column(name = "note")
    private String note;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MM-dd-yyyy HH:mm")
    @Column(name = "audit_date")
    private LocalDateTime auditDate;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MM-dd-yyyy")
    @Column(name = "last_updated_date")
    private LocalDate lastUpdatedDate;

    public PatientNote() {}

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public void setLoggedDate(LocalDate loggedDate) {
        this.loggedDate = loggedDate;
    }
    public void setNoteType(NoteType noteType) {
        this.noteType = noteType;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public void setAuditDate(LocalDateTime auditDate) {
        this.auditDate = auditDate;
    }
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
    public void setLastUpdatedDate(LocalDate lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public long getId() {
        return this.id;
    }
    public Patient getPatient() {
        return this.patient;
    }
    public LocalDate getLoggedDate() {
        return this.loggedDate;
    }
    public NoteType getNoteType() {
        return this.noteType;
    }
    public String getNote() {
        return this.note;
    }
    public LocalDateTime getAuditDate() {
        return this.auditDate;
    }
    public String getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }
    public LocalDate getLastUpdatedDate() {
        return this.lastUpdatedDate;
    }
}
