/*
 * Name: Mitchell Thompson
 * File: FileStorageProperties.java
 * Project: Data Viewer Application
 */

package com.example.application_viewer.components;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
/*
 * Component class that allows for access to a local
 * upload directory for PDF documents. This will
 * be irrelevant once file storage is moved.
 */
@Component
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private String uploadDir;

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
    
    public String getUploadDir() {
        return this.uploadDir;
    }
}
