package com.example.application_viewer.models;

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
@Table(name = "patient_diagnosis_codes")
public class PatientDiagnosisCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "description")
    private String description;

    public PatientDiagnosisCode() {}

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }
    public Patient getPatient() {
        return patient;
    }
    public String getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }
}
