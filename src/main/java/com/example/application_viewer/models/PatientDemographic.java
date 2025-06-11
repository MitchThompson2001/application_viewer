package com.example.application_viewer.models;

import java.time.LocalDate;

import com.example.application_viewer.components.PatientType;
import com.example.application_viewer.components.SexType;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "patient_demographics")
public class PatientDemographic {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Enumerated(EnumType.STRING)
    @Column(name = "patient_type")
    private PatientType patientType;

    @Column(name = "active")
    private boolean active;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "origin")
    private String origin;
    
    @Column(name = "email_a", unique = true)
    private String emailA;

    @Column(name = "email_b", unique = true)
    private String emailB;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MM-dd-yyyy")
    @Column(name = "dob")
    private LocalDate dob; // Date of Birth

    @Column(name = "phone_a", unique = true)
    private String phoneA;

    @Column(name = "phone_b", unique = true)
    private String phoneB;

    @Column(name = "ssn_last_four")
    private String ssnLastFour;

    @Column(name = "height")
    private String height;

    @Column(name = "weight")
    private String weight;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex")
    private SexType sex;

    @Column(name = "notes")
    private String notes;

    @Column(name = "home_company_num")
    private String homeCompanyNum;

    @Column(name = "home_company_loc")
    private String homeCompanyLoc;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MM-dd-yyyy")
    @Column(name = "last_updated")
    private LocalDate lastUpdated;

    public PatientDemographic() {}

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public void setPatientType(PatientType patientType) {
        this.patientType = patientType;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setOrigin(String origin) {
        this.origin = origin;
    }
    public void setEmailA(String emailA) {
        this.emailA = emailA;
    }
    public void setEmailB(String emailB) {
        this.emailB = emailB;
    }
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    public void setPhoneA(String phoneA) {
        this.phoneA = phoneA;
    }
    public void setPhoneB(String phoneB) {
        this.phoneB = phoneB;
    }
    public void setSsnLastFour(String ssnLastFour) {
        this.ssnLastFour = ssnLastFour;
    }
    public void setHeight(String height) {
        this.height = height;
    }
    public void setSex(SexType sex) {
        this.sex = sex;
    }
    public void setWeight(String weight) {
        this.weight = weight;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public void setHomeCompanyNum(String homeCompanyNum) {
        this.homeCompanyNum = homeCompanyNum;
    }
    public void setHomeCompanyLoc(String homeCompanyLoc) {
        this.homeCompanyLoc = homeCompanyLoc;
    }
    public void setLastUpdated(LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public long getId() {
        return this.id;
    }
    public Patient getPatient() {
        return this.patient;
    }
    public PatientType getPatientType() {
        return this.patientType;
    }
    public boolean isActive() {
        return this.active;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public String getOrigin() {
        return this.origin;
    }
    public String getEmailA() {
        return this.emailA;
    }
    public String getEmailB() {
        return this.emailB;
    }
    public LocalDate getDob() {
        return this.dob;
    }
    public String getPhoneA() {
        return this.phoneA;
    }
    public String getPhoneB() {
        return this.phoneB;
    }
    public String getSsnLastFour() {
        return this.ssnLastFour;
    }
    public String getHeight() {
        return this.height;
    }
    public String getWeight() {
        return this.weight;
    }
    public SexType getSex() {
        return this.sex;
    }
    public String getNotes() {
        return this.notes;
    }
    public String getHomeCompanyNum() {
        return this.homeCompanyNum;
    }
    public String getHomeCompanyLoc() {
        return this.homeCompanyLoc;
    }
    public LocalDate getLastUpdated() {
        return this.lastUpdated;
    }
}
