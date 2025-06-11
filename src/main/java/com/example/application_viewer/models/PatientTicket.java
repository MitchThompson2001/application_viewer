package com.example.application_viewer.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.application_viewer.components.BillType;
import com.example.application_viewer.components.OrderType;
import com.example.application_viewer.components.ProductCategory;
import com.example.application_viewer.components.ReasonType;
import com.example.application_viewer.components.ServiceType;
import com.example.application_viewer.components.TicketStatus;
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
@Table(name = "patient_tickets")
public class PatientTicket {
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

    @Column(name = "order_type")
    private OrderType orderType;

    @Column(name = "product_category")
    private ProductCategory productCategory;

    @Column(name = "ticket_status")
    private TicketStatus ticketStatus;

    @Column(name = "line_item_number")
    private String lineItemNumber;

    @Column(name = "service_type")
    private ServiceType serviceType;

    @Column(name = "bill_type")
    private BillType billType;

    @Column(name = "hcpcs_code")
    private String hcpcsCode;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MM-dd-yyyy")
    @Column(name = "dos")
    private LocalDate dos; // Date of Service

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "duration")
    private int duration; // Duration in days

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MM-dd-yyyy")
    @Column(name = "next_action")
    private LocalDate nextAction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "manufacturer_id")
    private String manufacturerId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "lot_number")
    private String lotNumber;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "tracking_number")
    private String trackingNumber;

    @Column(name = "notes")
    private String notes;

    @Column(name = "cost")
    private String cost;

    @Column(name = "billable")
    private boolean billable;

    @Column(name = "reason_type")
    private ReasonType reasonType;

    @Column(name = "reason_description")
    private String reasonDescription;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MM-dd-yyyy HH:mm")
    @Column(name = "audit_date")
    private LocalDateTime auditDate;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MM-dd-yyyy")
    @Column(name = "last_updated_date")
    private LocalDate lastUpdatedDate;

    public PatientTicket() {}

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public void setPatientOrder(PatientOrder patientOrder) {
        this.patientOrder = patientOrder;
    }
    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }
    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }
    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }
    public void setLineItemNumber(String lineItemNumber) {
        this.lineItemNumber = lineItemNumber;
    }
    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }
    public void setBillType(BillType billType) {
        this.billType = billType;
    }
    public void setHcpcsCode(String hcpcsCode) {
        this.hcpcsCode = hcpcsCode;
    }
    public void setDos(LocalDate dos) {
        this.dos = dos;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public void setNextAction(LocalDate nextAction) {
        this.nextAction = nextAction;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    public void setManufacturerId(String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public void setCost(String cost) {
        this.cost = cost;
    }
    public void setBillable(boolean billable) {
        this.billable = billable;
    }
    public void setReasonType(ReasonType reasonType) {
        this.reasonType = reasonType;
    }
    public void setReasonDescription(String reasonDescription) {
        this.reasonDescription = reasonDescription;
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
    public PatientOrder getPatientOrder() {
        return this.patientOrder;
    }
    public OrderType getOrderType() {
        return this.orderType;
    }
    public ProductCategory getProductCategory() {
        return this.productCategory;
    }
    public TicketStatus getTicketStatus() {
        return this.ticketStatus;
    }
    public String getLineItemNumber() {
        return this.lineItemNumber;
    }
    public ServiceType getServiceType() {
        return this.serviceType;
    }
    public BillType getBillType() {
        return this.billType;
    }
    public String getHcpcsCode() {
        return this.hcpcsCode;
    }
    public LocalDate getDos() {
        return this.dos;
    }
    public int getQuantity() {
        return this.quantity;
    }
    public int getDuration() {
        return this.duration;
    }
    public LocalDate getNextAction() {
        return this.nextAction;
    }
    public Location getLocation() {
        return this.location;
    }
    public String getManufacturer() {
        return this.manufacturer;
    }
    public String getManufacturerId() {
        return this.manufacturerId;
    }
    public String getProductName() {
        return this.productName;
    }
    public String getLotNumber() {
        return this.lotNumber;
    }
    public String getSerialNumber() {
        return this.serialNumber;
    }
    public String getTrackingNumber() {
        return this.trackingNumber;
    }
    public String getNotes() {
        return this.notes;
    }
    public String getCost() {
        return this.cost;
    }
    public boolean isBillable() {
        return this.billable;
    }
    public ReasonType getReasonType() {
        return this.reasonType;
    }
    public String getReasonDescription() {
        return this.reasonDescription;
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
