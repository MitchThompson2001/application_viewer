package com.example.application_viewer.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "patients")
public class Patient {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "email", unique = true)
    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MM-dd-yyyy")
    @Column(name = "dob")
    private Date dob;

    @Column(name = "phone")
    private String phone;

    public Patient() {}

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setDob(Date dob) {
        this.dob = dob;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getID() {
        return this.id;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public String getEmail() {
        return this.email;
    }
    public Date getDob() {
        return this.dob;
    }
    public String getPhone() {
        return this.phone;
    }
}
