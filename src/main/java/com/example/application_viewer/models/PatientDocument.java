/*
 * Name: Mitchell Thompson
 * File: Document.java
 * Project: Data Viewer Application
 */

package com.example.application_viewer.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.application_viewer.components.DocumentClass;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
/*
 * Entity class that defined the traits of a PDF for the purpose of storage
 * in a database
 */
@Entity
@Table(name = "patient_documents")
public class PatientDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "upload_date")
    private LocalDate uploadDate;

    @Column(name = "document_class")
    private DocumentClass documentClass;

    @Lob
    @Column(name = "pdf_file", nullable = false)
    private byte[] pdfFile;

    @Column(name = "notes")
    private String notes;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MM-dd-yyyy HH:mm")
    @Column(name = "audit_date")
    private LocalDateTime auditDate;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MM-dd-yyyy")
    @Column(name = "last_updated_date")
    private LocalDate lastUpdatedDate;

    @OneToOne(fetch = FetchType.LAZY)
    PatientAuthAndCert patientAuthAndCert;

    public PatientDocument() {}

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }
    public void setDocumentClass(DocumentClass documentClass) {
        this.documentClass = documentClass;
    }
    public void setPdfFile(byte[] pdfFile) {
        this.pdfFile = pdfFile.clone();
    }
    public void setNotes(String notes) {
        this.notes = notes;
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
    public void setPatientAuthAndCert(PatientAuthAndCert patientAuthAndCert) {
        this.patientAuthAndCert = patientAuthAndCert;
    }

    public long getId() {
        return this.id;
    }
    public Patient getPatient() {
        return this.patient;
    }
    public String getFileName() {
        return this.fileName;
    }
    public String getFilePath() {
        return this.filePath;
    }
    public LocalDate getUploadDate() {
        return this.uploadDate;
    }
    public DocumentClass getDocumentClass() {
        return this.documentClass;
    }
    public byte[] getPdfFile() {
        return this.pdfFile.clone();
    }
    public String getNotes() {
        return this.notes;
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
    public PatientAuthAndCert getPatientAuthAndCert() {
        return this.patientAuthAndCert;
    }
}
