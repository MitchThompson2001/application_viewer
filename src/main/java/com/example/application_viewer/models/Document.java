package com.example.application_viewer.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
/*
 * Entity class that defined the traits of a PDF for the purpose of storage
 * in a database
 */
@Entity
@Table(name = "documents")
public class Document {

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

    @Lob
    @Column(name = "pdf_file", nullable = false)
    private byte[] pdfFile;

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public void setPdfFile(byte[] pdfFile) {
        this.pdfFile = pdfFile.clone();
    }

    public long getId() {
        return this.id;
    }
    public byte[] getPdfFile() {
        return this.pdfFile;
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
    public Patient getPatient() {
        return this.patient;
    }

}
