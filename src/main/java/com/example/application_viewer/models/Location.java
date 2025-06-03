/*
 * Name: Mitchell Thompson
 * File: Location.java
 * Project: Data Viewer Application
 */

package com.example.application_viewer.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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

    public Location() {}

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
}

