package com.example.application_viewer.models;

import com.example.application_viewer.components.AddressType;
import com.example.application_viewer.components.ResidenceType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "patient_addresses")
public class PatientAddress {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
  
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "active")
    private boolean active;

    @Enumerated(EnumType.STRING)
    @Column(name = "address_type")
    private AddressType addressType;

    @Enumerated(EnumType.STRING)
    @Column(name = "residence_type")
    private ResidenceType residenceType;

    @Column(name = "address_a")
    private String addressA;

    @Column(name = "address_b")
    private String addressB;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "county")
    private String county;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "country")
    private String country;

    @Column(name = "geo_code")
    private String geoCode;

    @Column(name = "electric_company")
    private String electricCompany;

    @Column(name = "service_loc_num")
    private String serviceLocNum;

    public PatientAddress() {}

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }
    public void setResidenceType(ResidenceType residenceType) {
        this.residenceType = residenceType;
    }
    public void setAddressA(String addressA) {
        this.addressA = addressA;
    }
    public void setAddressB(String addressB) {
        this.addressB = addressB;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setState(String state) {
        this.state = state;
    }
    public void setCounty(String county) {
        this.county = county;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public void setGeoCode(String geoCode) {
        this.geoCode = geoCode;
    }
    public void setElectricCompany(String electricCompany) {
        this.electricCompany = electricCompany;
    }
    public void setServiceLocNum(String serviceLocNum) {
        this.serviceLocNum = serviceLocNum;
    }
    
    public long getId() {
        return this.id;
    }
    public Patient getPatient() {
        return this.patient;
    }
    public boolean isActive() {
        return this.active;
    }
    public AddressType getAddressType() {
        return this.addressType;
    }
    public ResidenceType getResidenceType() {
        return this.residenceType;
    }
    public String getAddressA() {
        return this.addressA;
    }
    public String getAddressB() {
        return this.addressB;
    }
    public String getCity() {
        return this.city;
    }
    public String getState() {
        return this.state;
    }
    public String getCounty() {
        return this.county;
    }
    public String getZipCode() {
        return this.zipCode;
    }
    public String getCountry() {
        return this.country;
    }
    public String getGeoCode() {
        return this.geoCode;
    }
    public String getElectricCompany() {
        return this.electricCompany;
    }
    public String getServiceLocNum() {
        return this.serviceLocNum;
    }
}
