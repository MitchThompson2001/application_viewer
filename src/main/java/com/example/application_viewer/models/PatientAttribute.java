package com.example.application_viewer.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "patient_attributes")
public class PatientAttribute {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "active")
    private boolean active;

    @Column(name = "effective_date")
    private String effectiveDate;

    @Column(name = "attribute")
    private String attribute;

    public PatientAttribute() {}

    public String setAttribute() {
        return attribute;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public long getId() {
        return this.id;
    }
    public Patient getPatient() {
        return this.patient;
    }
    public boolean isActive() {
        return this.active;
    }
    public String getEffectiveDate() {
        return this.effectiveDate;
    }
    public String getAttribute() {
        return this.attribute;
    }
}
