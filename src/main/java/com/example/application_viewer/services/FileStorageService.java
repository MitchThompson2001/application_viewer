/*
 * Name: Mitchell Thompson
 * File: FileStorageService.java
 * Project: Data Viewer Application
 */

package com.example.application_viewer.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.application_viewer.components.FileStorageProperties;

/*
 * Service class that manages the local file directory for storing PDF files. 
 * Currently the implementation is only intended to store PDF files, but
 * this should be able to manage any file type
 */
@Service
public class FileStorageService {


    private final Path fileStorageLocation;

    public List<String> listAllFiles() {
        try (Stream<Path> stream = Files.walk(this.fileStorageLocation, 1)) {
            return stream
                    .filter(path -> !path.equals(this.fileStorageLocation)) // exclude root dir
                    .map(this.fileStorageLocation::relativize)
                    .map(Path::toString)
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            throw new RuntimeException("Could not list files", ex);
        }
    }

    public FileStorageService(FileStorageProperties properties) throws IOException {
        this.fileStorageLocation = Paths.get(properties.getUploadDir()).toAbsolutePath().normalize();
        Files.createDirectories(this.fileStorageLocation);
    }

    public String storeFile(MultipartFile file) throws IOException {
        // String fileName = UUID.randomUUID() + "_" + StringUtils.cleanPath(file.getOriginalFilename());
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            originalFilename = "";  // or some safe default
        }
        String fileName = StringUtils.cleanPath(originalFilename);
        Path targetLocation = this.fileStorageLocation.resolve(fileName);
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        
        return fileName;
    }

    public Resource loadFileAsResource(String fileName) throws MalformedURLException {
        Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
        return new UrlResource(filePath.toUri());
    }
}
