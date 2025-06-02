package com.example.application_viewer.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "email", unique = true)
    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MM-dd-yyyy")
    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "phone")
    private String phone;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Document> documents = new HashSet<>();

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
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getId() {
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
    public LocalDate getDob() {
        return this.dob;
    }
    public String getPhone() {
        return this.phone;
    }

    public void addDocument(Document document) {
        this.documents.add(document);
    }
    public void removeDocumentById(Long id) {
        for (Document doc : this.documents) {
            if (doc.getId() == id) {
                this.documents.remove(doc);
                break;
            }
        }
    }
}
