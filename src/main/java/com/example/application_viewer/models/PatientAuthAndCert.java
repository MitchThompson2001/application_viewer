package com.example.application_viewer.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.application_viewer.components.AuthorizationLimitType;
import com.example.application_viewer.components.AuthorizationType;
import com.example.application_viewer.components.BillType;
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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "patient_auths_and_certs")
public class PatientAuthAndCert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_order_id", nullable = false)
    private PatientOrder patientOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_ticket_id", nullable = false)
    private PatientTicket patientTicket;

    @Column(name = "order_type")
    private OrderType orderType;

    @Column(name = "auth_cert_number")
    private String authCertNumber;

    @Column(name = "auth_cert_type")
    private AuthorizationType authCertType;

    @Column(name = "plan_code")
    private String planCode;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_document_id")
    private PatientDocument patientDocument;

    @Column(name = "bill_type")
    private BillType billType;

    @Column(name = "hcpcs_code")
    private String hcpcsCode;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MM-dd-yyyy")
    @Column(name = "ticket_dos")
    private LocalDate ticketDos;

    @Column(name = "description")
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MM-dd-yyyy")
    @Column(name = "start_date")
    private LocalDate startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MM-dd-yyyy")
    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "receiver_first_name")
    private String receiverFirstName;

    @Column(name = "receiver_last_name")
    private String receiverLastName;

    @Column(name = "receiver_position")
    private String receiverPosition;

    @Column(name = "duration")
    private int duration; // Duration in hours?

    @Column(name = "bypass_reason")
    private String bypassReason;

    @Column(name = "notes")
    private String notes;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MM-dd-yyyy HH:mm")
    @Column(name = "audit_date")
    private LocalDateTime auditDate;

    @Column(name = "authorization_limit")
    private String authorizationLimit;

    @Column(name = "authorization_limit_type")
    private AuthorizationLimitType authorizationLimitType;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MM-dd-yyyy")
    @Column(name = "last_updated_date")
    private LocalDate lastUpdatedDate;

    public PatientAuthAndCert() {}

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public void setPatientOrder(PatientOrder patientOrder) {
        this.patientOrder = patientOrder;
    }
    public void setPatientTicket(PatientTicket patientTicket) {
        this.patientTicket = patientTicket;
    }
    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }
    public void setAuthCertNumber(String authCertNumber) {
        this.authCertNumber = authCertNumber;
    }
    public void setAuthCertType(AuthorizationType authCertType) {
        this.authCertType = authCertType;
    }
    public void setPlanCode(String planCode) {
        this.planCode = planCode;
    }
    public void setDocument(PatientDocument patientDocument) {
        this.patientDocument = patientDocument;
    }
    public void setBillType(BillType billType) {
        this.billType = billType;
    }
    public void setHcpcsCode(String hcpcsCode) {
        this.hcpcsCode = hcpcsCode;
    }
    public void setTicketDos(LocalDate ticketDos) {
        this.ticketDos = ticketDos;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
    public void setReceiverFirstName(String receiverFirstName) {
        this.receiverFirstName = receiverFirstName;
    }
    public void setReceiverLastName(String receiverLastName) {
        this.receiverLastName = receiverLastName;
    }
    public void setReceiverPosition(String receiverPosition) {
        this.receiverPosition = receiverPosition;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public void setBypassReason(String bypassReason) {
        this.bypassReason = bypassReason;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public void setAuditDate(LocalDateTime auditDate) {
        this.auditDate = auditDate;
    }
    public void setAuthorizationLimit(String authorizationLimit) {
        this.authorizationLimit = authorizationLimit;
    }
    public void setAuthorizationLimitType(AuthorizationLimitType authorizationLimitType) {
        this.authorizationLimitType = authorizationLimitType;
    }
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
    public void setLastUpdatedDate(LocalDate lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public long getId() {
        return id;
    }
    public Patient getPatient() {
        return this.patient;
    }
    public PatientOrder getPatientOrder() {
        return this.patientOrder;
    }
    public PatientTicket getPatientTicket() {
        return this.patientTicket;
    }
    public OrderType getOrderType() {
        return this.orderType;
    }
    public String getAuthCertNumber() {
        return this.authCertNumber;
    }
    public AuthorizationType getAuthCertType() {
        return this.authCertType;
    }
    public String getPlanCode() {
        return this.planCode;
    }
    public PatientDocument getPatientDocument() {
        return this.patientDocument;
    }
    public BillType getBillType() {
        return this.billType;
    }
    public String getHcpcsCode() {
        return this.hcpcsCode;
    }
    public LocalDate getTicketDos() {
        return this.ticketDos;
    }
    public String getDescription() {
        return this.description;
    }
    public LocalDate getStartDate() {
        return this.startDate;
    }
    public LocalDate getDueDate() {
        return this.dueDate;
    }
    public String getReceiverFirstName() {
        return this.receiverFirstName;
    }
    public String getReceiverLastName() {
        return this.receiverLastName;
    }
    public String getReceiverPosition() {
        return this.receiverPosition;
    }
    public int getDuration() {
        return this.duration;
    }
    public String getBypassReason() {
        return this.bypassReason;
    }
    public String getNotes() {
        return notes;
    }
    public LocalDateTime getAuditDate() {
        return this.auditDate;
    }
    public String getAuthorizationLimit() {
        return this.authorizationLimit;
    }
    public AuthorizationLimitType getAuthorizationLimitType() {
        return this.authorizationLimitType;
    }
    public String getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }
    public LocalDate getLastUpdatedDate() {
        return this.lastUpdatedDate;
    }
}
