package com.example.application_viewer.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.example.application_viewer.components.OrderType;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "patient_orders")
public class PatientOrder {
    // Columns

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "order_type")
    private OrderType orderType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MM-dd-yyyy")
    @Column(name = "doctor_order_date")
    private LocalDate doctorOrderDate;

    @Column(name = "doctor_first_name")
    private String doctorFirstName;

    @Column(name = "doctor_last_name")
    private String doctorLastName;

    @Column(name = "doctor_npi")
    private String doctorNpi;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MM-dd-yyyy")
    @Column(name = "order_start_date")
    private LocalDate orderStartDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MM-dd-yyyy")
    @Column(name = "order_due_date")
    private LocalDate orderDueDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MM-dd-yyyy")
    @Column(name = "order_completed_date")
    private LocalDate orderCompletedDate;

    @Column(name = "instructions")
    private String instructions;

    @Column(name = "diagnostic_codes")
    private String diagnosticCodes;

    @Column(name = "qualified")
    private boolean qualified;

    @Column(name = "referral_source")
    private String referralSource;

    @Column(name = "referral_source_npi")
    private String referralSourceNpi;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MM-dd-yyyy")
    @Column(name = "referral_date")
    private LocalDate referralDate;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MM-dd-yyyy")
    @Column(name = "last_updated_date")
    private LocalDate lastUpdatedDate;

    // Variables

    @OneToMany(mappedBy = "patientOrder")
    private final Set<PatientTicket> tickets;

    @OneToMany(mappedBy = "patientOrder")
    private final Set<PatientTransaction> transactions;

    public PatientOrder() {
        tickets = new HashSet<>();
        transactions = new HashSet<>();
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }
    public void setDoctorOrderDate(LocalDate doctorOrderDate) {
        this.doctorOrderDate = doctorOrderDate;
    }
    public void setDoctorFirstName(String doctorFirstName) {
        this.doctorFirstName = doctorFirstName;
    }
    public void setDoctorLastName(String doctorLastName) {
        this.doctorLastName = doctorLastName;
    }
    public void setDoctorNpi(String doctorNpi) {
        this.doctorNpi = doctorNpi;
    }
    public void setOrderStartDate(LocalDate orderStartDate) {
        this.orderStartDate = orderStartDate;
    }
    public void setOrderDueDate(LocalDate orderDueDate) {
        this.orderDueDate = orderDueDate;
    }
    public void setOrderCompletedDate(LocalDate orderCompletedDate) {
        this.orderCompletedDate = orderCompletedDate;
    }
    public void setIntructions(String instructions) {
        this.instructions = instructions;
    }
    public void setDiagnosticCodes(String diagnosticCodes) {
        this.diagnosticCodes = diagnosticCodes;
    }
    public void setQualified(boolean qualified) {
        this.qualified = qualified;
    }
    public void setReferralSource(String referralSource) {
        this.referralSource = referralSource;
    }
    public void setReferralSourceNpi(String referralSourceNpi) {
        this.referralSourceNpi = referralSourceNpi;
    }
    public void setReferralDate(LocalDate referralDate) {
        this.referralDate = referralDate;
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
    public OrderType getOrderType() {
        return this.orderType;
    }
    public LocalDate getDoctorOrderDate() {
        return this.doctorOrderDate;
    }
    public String getDoctorFirstName() {
        return this.doctorFirstName;
    }
    public String getDoctorLastname() {
        return this.doctorLastName;
    }
    public String getDoctorNpi() {
        return this.doctorNpi;
    }
    public LocalDate getOrderStartDate() {
        return this.orderStartDate;
    }
    public LocalDate getOrderDueDate() {
        return this.orderDueDate;
    }
    public LocalDate getOrderCompletedDate() {
        return this.orderCompletedDate;
    }
    public String getInstructions() {
        return this.instructions;
    }
    public String getDiagnosticCodes() {
        return this.diagnosticCodes;
    }
    public boolean isQualified() {
        return this.qualified;
    }
    public String getReferralSource() {
        return this.referralSource;
    }
    public String getReferralSourceNpi() {
        return this.referralSourceNpi;
    }
    public LocalDate getReferralDate() {
        return this.referralDate;
    }
    public String getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }
    public LocalDate getLastUpdatedDate() {
        return this.lastUpdatedDate;
    }

    public void addTicket(PatientTicket patientTicket) {
        tickets.add(patientTicket);
    }

    public void removeTicket(PatientTicket patientTicket) {
        tickets.remove(patientTicket);
    }

    public void addTransaction(PatientTransaction transaction) {
        transactions.add(transaction);
    }
    
    public void removeTransaction(PatientTransaction transaction) {
        transactions.remove(transaction);
    }

}
