/*
 * Name: Mitchell Thompson
 * File: Patient.java
 * Project: Data Viewer Application
 */

package com.example.application_viewer.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/*
 * Entity class that defines the traits of a patient for storage in a database
 */
@Entity
@Table(name = "patients")
public class Patient {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToOne(
        mappedBy = "patient", 
        cascade = CascadeType.ALL, 
        orphanRemoval = true)
    private PatientAddress patientAddress;

    @OneToOne(
        mappedBy = "patient", 
        cascade = CascadeType.ALL, 
        orphanRemoval = true)
    private PatientAttribute patientAttribute;

    @OneToMany(
        mappedBy = "patient", 
        cascade = CascadeType.ALL, 
        orphanRemoval = true)
    private final Set<PatientAuthAndCert> patientAuthAndCerts = new HashSet<>();

    @OneToMany(
        mappedBy = "patient", 
        cascade = CascadeType.ALL, 
        orphanRemoval = true)
    private final Set<PatientContact> patientContacts = new HashSet<>();

    @OneToOne(
        mappedBy = "patient", 
        cascade = CascadeType.ALL, 
        orphanRemoval = true)
    private PatientDemographic patientDemographic;

    @OneToMany(
        mappedBy = "patient", 
        cascade = CascadeType.ALL, 
        orphanRemoval = true)
    private final Set<PatientDiagnosisCode> patientDiagnosisCodes = new HashSet<>();

    @OneToMany(
        mappedBy = "patient", 
        cascade = CascadeType.ALL, 
        orphanRemoval = true)
    private final Set<PatientDocument> patientDocuments = new HashSet<>();

    @OneToOne(
        mappedBy = "patient", 
        cascade = CascadeType.ALL, 
        orphanRemoval = true)
    private PatientInsurance patientInsurance;

    @OneToMany(
        mappedBy = "patient", 
        cascade = CascadeType.ALL, 
        orphanRemoval = true)
    private final Set<PatientNote> patientNotes = new HashSet<>();

    @OneToMany(
        mappedBy = "patient", 
        cascade = CascadeType.ALL, 
        orphanRemoval = true)
    private final Set<PatientOrder> patientOrders = new HashSet<>();

    @OneToMany(
        mappedBy = "patient", 
        cascade = CascadeType.ALL, 
        orphanRemoval = true)
    private final Set<PatientTicket> patientTickets = new HashSet<>();

    @OneToMany(
        mappedBy = "patient", 
        cascade = CascadeType.ALL, 
        orphanRemoval = true)
    private final Set<PatientTransaction> patientTransactions = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MM-dd-yyyy")
    @Column(name = "last_updated_date")
    private LocalDate lastUpdatedDate;


    public Patient() {}

    public void setPatientAddress(PatientAddress patientAddress) {
        this.patientAddress = patientAddress;
    } 
    public void setPatientAttribute(PatientAttribute patientAttribute) {
        this.patientAttribute = patientAttribute;
    }
    public void setPatientDemographic(PatientDemographic patientDemographic) {
        this.patientDemographic = patientDemographic;
    }
    public void setPatientInsurance(PatientInsurance patientInsurance) {
        this.patientInsurance = patientInsurance;
    }
    public void setLocation(Location location) {
        this.location = location;
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
    public PatientAddress getPatientAddress() {
        return this.patientAddress;
    }
    public PatientAttribute getPatientAttribute() {
        return this.patientAttribute;
    }
    public Set<PatientAuthAndCert> getPatientAuthAndCerts() {
        return this.patientAuthAndCerts;
    }
    public PatientAuthAndCert getPatientAuthAndCert(long id) {
        PatientAuthAndCert temp = null;
        for(PatientAuthAndCert val : this.patientAuthAndCerts) {
            if (val.getId() == id) {
                temp = val;
                break;
            }
        }
        return temp;
    }
    public Set<PatientContact> getPatientContacts() {
        return this.patientContacts;
    }
    public PatientContact getPatientContact(long id) {
        PatientContact temp = null;
        for(PatientContact val : this.patientContacts) {
            if (val.getId() == id) {
                temp = val;
                break;
            }
        }
        return temp;
    }
    public PatientDemographic getPatientDemographic() {
        return this.patientDemographic;
    }
    public Set<PatientDiagnosisCode> getPatientDiagnosisCodes() {
        return this.patientDiagnosisCodes;
    }
    public PatientDiagnosisCode getPatientDiagnosisCode(long id) {
        PatientDiagnosisCode temp = null;
        for(PatientDiagnosisCode val : this.patientDiagnosisCodes) {
            if (val.getId() == id) {
                temp = val;
                break;
            }
        }
        return temp;
    }
    public Set<PatientDocument> getPatientDocuments() {
        return this.patientDocuments;
    }
    public PatientDocument getPatientDocument(long id) {
        PatientDocument temp = null;
        for(PatientDocument val : this.patientDocuments) {
            if (val.getId() == id) {
                temp = val;
                break;
            }
        }
        return temp;
    }
    public PatientInsurance getPatientInsurance() {
        return this.patientInsurance;
    }
    public Set<PatientNote> getPatientNotes() {
        return this.patientNotes;
    }
    public PatientNote getPatientNote(long id) {
        PatientNote temp = null;
        for(PatientNote val : this.patientNotes) {
            if (val.getId() == id) {
                temp = val;
                break;
            }
        }
        return temp;
    }
    public Set<PatientOrder> getPatientOrders() {
        return this.patientOrders;
    }
    public PatientOrder getPatientOrder(long id) {
        PatientOrder temp = null;
        for(PatientOrder val : this.patientOrders) {
            if (val.getId() == id) {
                temp = val;
                break;
            }
        }
        return temp;
    }
    public Set<PatientTicket> getPatientTickets() {
        return this.patientTickets;
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
    public Location getLocation() {
        return this.location;
    }
    public String getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }
    public LocalDate getLastUpdatedDate() {
        return this.lastUpdatedDate;
    }

    public void addPatientAuthAndCert(PatientAuthAndCert patientAuthAndCert) {
        this.patientAuthAndCerts.add(patientAuthAndCert);
    }
    public void addPatientContact(PatientContact patientContact) {
        this.patientContacts.add(patientContact);
    }
    public void addPatientDiagnosisCode(PatientDiagnosisCode patientDiagnosisCode) {
        this.patientDiagnosisCodes.add(patientDiagnosisCode);
    }
    public void addPatientDocument(PatientDocument patientDocument) {
        this.patientDocuments.add(patientDocument);
    }
    public void addPatientNote(PatientNote patientNote) {
        this.patientNotes.add(patientNote);
    }
    public void addPatientOrder(PatientOrder patientOrder) {
        this.patientOrders.add(patientOrder);
    }
    public void addPatientTicket(PatientTicket patientTicket) {
        this.patientTickets.add(patientTicket);
    }
    public void addPatientTransaction(PatientTransaction patientTransaction) {
        this.patientTransactions.add(patientTransaction);
    }

    public void removePatientAuthAndCert(PatientAuthAndCert patientAuthAndCert) {
        this.patientAuthAndCerts.remove(patientAuthAndCert);
    }
    public void removePatientContact(PatientContact patientContact) {
        this.patientContacts.remove(patientContact);
    }
    public void removePatientDiagnosisCode(PatientDiagnosisCode patientDiagnosisCode) {
        this.patientDiagnosisCodes.remove(patientDiagnosisCode);
    }
    public void removePatientDocument(PatientDocument patientDocument) {
        this.patientDocuments.remove(patientDocument);
    }
    public void removePatientNote(PatientNote patientNote) {
        this.patientNotes.remove(patientNote);
    }
    public void removePatientOrder(PatientOrder patientOrder) {
        this.patientOrders.remove(patientOrder);
    }
    public void removePatientTicket(PatientTicket patientTicket) {
        this.patientTickets.remove(patientTicket);
    }
    public void removePatientTransaction(PatientTransaction patientTransaction) {
        this.patientTransactions.remove(patientTransaction);
    }
}
