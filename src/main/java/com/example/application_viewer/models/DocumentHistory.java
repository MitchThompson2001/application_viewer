/*
 * Name: Mitchell Thompson
 * File: DocumentHistory.java
 * Project: Data Viewer Application
 */

package com.example.application_viewer.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/*
 * Entity class that defines the traits for managing view of user access 
 * history
 */
@Entity
public class DocumentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    private String action;

    private String fileName;

    private LocalDateTime timestamp;

    public DocumentHistory() {}

    public DocumentHistory(
        String username, 
        String action, 
        String fileName, 
        LocalDateTime timestamp) {
            
        this.username = username;
        this.action = action;
        this.fileName = fileName;
        this.timestamp = timestamp;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setAction(String action) {
        this.action = action;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public long getId() {
        return this.id;
    }
    public String getUsername() {
        return this.username;
    }
    public String getAction() {
        return this.action;
    }
    public String getFileName() {
        return this.fileName;
    }
    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }
}

