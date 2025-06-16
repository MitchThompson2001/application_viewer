/*
 * Name: Mitchell Thompson
 * File: Location.java
 * Project: Data Viewer Application
 */

package com.example.application_viewer.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/*
 * Entity class that defines the traits of a physical business location
 * for the purpose of storage in a database
 */
@Entity
@Table(name = "locations")
public class Location {
    
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "fax_number")
    private String faxNumber;

    @Column(name = "location_name")
    private String locationName;

    @Column(name = "street_address")
    private String streetAddress;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip_code")
    private String zipCode;

    @OneToMany(
        mappedBy = "location", 
        cascade = CascadeType.ALL, 
        orphanRemoval = true)
    private final Set<Patient> patients;

    @OneToMany(
        mappedBy = "location", 
        cascade = CascadeType.ALL, 
        orphanRemoval = true)
    private final Set<PatientTransaction> patientTransactions;

    @OneToMany(
        mappedBy = "location", 
        cascade = CascadeType.ALL, 
        orphanRemoval = true)
    private final Set<PatientTicket> patientTickets;

    public Location() {
        patients = new HashSet<>();
        patientTransactions = new HashSet<>();
        patientTickets = new HashSet<>();
    }

    public void setLocationID(long id) {
        this.id = id;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setState(String state) {
        this.state = state;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public long getId() {
        return this.id;
    }
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    public String getFaxNumber() {
        return this.faxNumber;
    }
    public String getLocationName() {
        return this.locationName;
    }
    public String getStreetAddress() {
        return this.streetAddress;
    }
    public String getCity() {
        return this.city;
    }
    public String getState() {
        return this.state;
    }
    public String getZipCode() {
        return this.zipCode;
    }
    public Set<Patient> getPatients() {
        return this.patients;
    }
    public Patient getPatient(long id) {
        Patient temp = null;
        for(Patient val : this.patients) {
            if (val.getId() == id) {
                temp = val;
                break;
            }
        }
        return temp;
    }
    public Set<PatientTransaction> getPatientTransactions() {
        return this.patientTransactions;
    }
    public PatientTransaction getPatientTransaction(long id) {
        PatientTransaction temp = null;
        for(PatientTransaction val : this.patientTransactions) {
            if (val.getId() == id) {
                temp = val;
                break;
            }
        }
        return temp;
    }
    public PatientTicket getPatientTicket(long id) {
        PatientTicket temp = null;
        for(PatientTicket val : this.patientTickets) {
            if (val.getId() == id) {
                temp = val;
                break;
            }
        }
        return temp;
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }
    public void addPatientTransaction(PatientTransaction patientTransaction) {
        patientTransactions.add(patientTransaction);
    }
    public void addPatientTicket(PatientTicket patientTicket) {
        patientTickets.add(patientTicket);
    }

    public void removePatient(Patient patient) {
        patients.remove(patient);
    }
    public void removePatientTransaction(PatientTransaction patientTransaction) {
        patientTransactions.remove(patientTransaction);
    }
    public void removePatientTicket(PatientTicket patientTicket) {
        patientTickets.remove(patientTicket);
    }
}

