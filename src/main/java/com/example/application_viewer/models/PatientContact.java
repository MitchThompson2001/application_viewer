package com.example.application_viewer.models;

import com.example.application_viewer.components.EmailType;
import com.example.application_viewer.components.PhoneType;
import com.example.application_viewer.components.RelationshipType;

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
@Table(name = "patient_contacts")
public class PatientContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "relationship")
    private RelationshipType relationship;

    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "phone_type")
    private PhoneType phoneType;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "email_type")
    private EmailType emailType;

    @Column(name = "emergency_contact")
    private boolean emergencyContact;

    @Column(name = "notes")
    private String notes;

    public PatientContact() {}

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setRelationship(RelationshipType relationship) {
        this.relationship = relationship;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setPhoneType(PhoneType phoneType) {
        this.phoneType = phoneType;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setEmailType(EmailType emailType) {
        this.emailType = emailType;
    }
    public void setEmergencyContact(boolean emergencyContact) {
        this.emergencyContact = emergencyContact;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

    public long getId() {
        return id;
    }
    public Patient getPatient() {
        return patient;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public RelationshipType getRelationship() {
        return relationship;
    }
    public String getPhone() {
        return phone;
    }
    public PhoneType getPhoneType() {
        return phoneType;
    }
    public String getEmail() {
        return email;
    }
    public EmailType getEmailType() {
        return emailType;
    }
    public boolean isEmergencyContact() {
        return emergencyContact;
    }
    public String getNotes() {
        return notes;
    }
}
