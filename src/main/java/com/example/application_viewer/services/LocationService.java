/*
 * Name: Mitchell Thompson
 * File: LocationService.java
 * Project: Data Viewer Application
 */

package com.example.application_viewer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.application_viewer.models.Location;
import com.example.application_viewer.repositories.LocationRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LocationService {
    @Autowired private LocationRepository locationRepository;

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public List<Location> searchAndSortLocations(
        String phoneNumber, 
        String faxNumber, 
        String locationName, 
        String streetAddress,
        String city,
        String state,
        String zipCode, 
        String sortField, 
        String sortDir) {

        Specification<Location> spec = (root, query, cb) -> cb.conjunction();

        if (phoneNumber != null && !phoneNumber.isEmpty()) {
        spec = spec.and((root, query, cb) -> 
            cb.like(cb.lower(root.get("phoneNumber")), 
            "%" + phoneNumber + "%"));
        }
        
        if (faxNumber != null && !faxNumber.isEmpty()) {
        spec = spec.and((root, query, cb) -> 
            cb.like(cb.lower(root.get("faxNumber")), 
            "%" + faxNumber + "%"));
        }

        if (locationName != null && !locationName.isEmpty()) {
            spec = spec.and((root, query, cb) -> 
                cb.like(cb.lower(root.get("locationName")), 
                "%" + locationName.toLowerCase() + "%"));
        }

        if (streetAddress != null && !streetAddress.isEmpty()) {
            spec = spec.and((root, query, cb) -> 
                cb.like(cb.lower(root.get("streetAddress")), 
                "%" + streetAddress.toLowerCase() + "%"));
        }

        if (city != null && !city.isEmpty()) {
            spec = spec.and((root, query, cb) -> 
                cb.like(cb.lower(root.get("city")), 
                "%" + city.toLowerCase() + "%"));
        }

        if (state != null && !state.isEmpty()) {
            spec = spec.and((root, query, cb) -> 
                cb.like(cb.lower(root.get("state")), 
                "%" + state.toLowerCase() + "%"));
        }

        if (zipCode != null && !zipCode.isEmpty()) {
            spec = spec.and((root, query, cb) -> 
                cb.like(cb.lower(root.get("zipCode")), 
                "%" + zipCode + "%"));
        }

        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortField);

        return locationRepository.findAll(spec, sort);    
    }

    public void save(Location location) {
        locationRepository.save(location);
    }

    public Location getByID(long id) {
        return locationRepository.findById(id)
            .orElseThrow(() -> 
            new EntityNotFoundException("Entity not found for ID " + id));
    }
    
    public void deleteByID(long id) {
        locationRepository.deleteById(id);
    }
}
