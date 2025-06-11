package com.example.application_viewer.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.application_viewer.components.BillType;
import com.example.application_viewer.components.TransactionSubType;
import com.example.application_viewer.components.TransactionType;
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
@Table(name = "patient_transactions")
public class PatientTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_order_id", nullable = false)
    private PatientOrder patientOrder;

    @Column(name = "HCPC")
    private String hcpc;

    @Column(name = "bill_type")
    private BillType billType;

    @Column(name = "cost")
    private double cost;

    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Column(name = "transaction_sub_type")
    private TransactionSubType transactionSubType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MM-dd-yyyy")
    @Column(name = "transaction_date")
    private LocalDate transactionDate;

    @Column(name = "insurance_id")
    private String insuranceId;

    @Column(name = "insurance_name")
    private String insuranceName;

    @Column(name = "claim_number")
    private String claimNumber;

    @Column(name = "notes")
    private String notes;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MM-dd-yyyy HH:mm")
    @Column(name = "audit_date")
    private LocalDateTime auditDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MM-dd-yyyy HH:mm")
    @Column(name = "audit_update_date")
    private LocalDateTime auditUpdateDate;

    public PatientTransaction() {}

    public void setLocation(Location location) {
        this.location = location;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public void setPatientOrder(PatientOrder patientOrder) {
        this.patientOrder = patientOrder;
    }
    public void setHcpc(String hcpc) {
        this.hcpc = hcpc;
    }
    public void setBillType(BillType billType) {
        this.billType = billType;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }
    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
    public void setTransactionSubType(TransactionSubType transactionSubType) {
        this.transactionSubType = transactionSubType;
    }
    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }
    public void setInsuranceId(String insuranceId) {
        this.insuranceId = insuranceId;
    }
    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }
    public void setClaimNumber(String claimNumber) {
        this.claimNumber = claimNumber;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public void setAuditDate(LocalDateTime auditDate) {
        this.auditDate = auditDate;
    }
    public void setAuditUpdateDate(LocalDateTime auditUpdateDate) {
        this.auditUpdateDate = auditUpdateDate;
    }

    public long getId() {
        return this.id;
    }
    public Location getLocation() {
        return this.location;
    }
    public Patient getPatient() {
        return this.patient;
    }
    public PatientOrder getPatientOrder() {
        return this.patientOrder;
    }
    public String getHcpc() {
        return this.hcpc;
    }
    public BillType getBillType() {
        return this.billType;
    }
    public double getCost() {
        return this.cost;
    }
    public TransactionType getTransactionType() {
        return this.transactionType;
    }
    public TransactionSubType getTransactionSubType() {
        return this.transactionSubType;
    }
    public LocalDate getTransactionDate() {
        return this.transactionDate;
    }
    public String getInsuranceid() {
        return this.insuranceId;
    }
    public String getInsuranceName() {
        return this.insuranceName;
    }
    public String claimNumber() {
        return this.claimNumber;
    }
    public String getNotes() {
        return this.notes;
    }
    public LocalDateTime getAuditDate() {
        return this.auditDate;
    }
    public LocalDateTime getAuditUpdateDate() {
        return this.auditUpdateDate;
    }
}
