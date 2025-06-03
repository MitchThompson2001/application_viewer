/*
 * Name: Mitchell Thompson
 * File: LocationController.java
 * Project: Data Viewer Application
 */

package com.example.application_viewer.controllers;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.application_viewer.models.Location;
import com.example.application_viewer.services.LocationService;

/*
 * Controller class that maintains http requests relating to the Location
 * class
 */
@Controller
public class LocationController {
    @Autowired private LocationService locationService;

    /*
     * Returns the result of a search query for any location with data that
     * matches the query. If no query is provided, no locations will be returned.
     */
    @GetMapping("/location_list")
    public String searchLocation(
        @RequestParam(required = false) Long id,
        @RequestParam(required = false) String phoneNumber,
        @RequestParam(required = false) String faxNumber,
        @RequestParam(required = false) String locationName,
        @RequestParam(required = false) String streetAddress,
        @RequestParam(required = false) String city,
        @RequestParam(required = false) String state,
        @RequestParam(required = false) String zipCode,
        @RequestParam(required = false, defaultValue = "id") String sortField,
        @RequestParam(required = false, defaultValue = "asc") String sortDir,
        Model model) {
            
        boolean hasSearchInput = Stream.of(
            id, 
            phoneNumber, 
            faxNumber, 
            locationName, 
            streetAddress, 
            city, 
            state, 
            zipCode).anyMatch(Objects::nonNull);

        List<Location> locations;
        if (hasSearchInput) {
            locations = locationService.searchAndSortLocations(
                id,
                phoneNumber, 
                faxNumber, 
                locationName, 
                streetAddress, 
                city, 
                state, 
                zipCode, 
                sortField, 
                sortDir);
        } else {
            // Show all locations sorted, even if no filters
            locations = locationService.searchAndSortLocations(
                null,
                null, 
                null,
                null, 
                null, 
                null, 
                null, 
                null, 
                sortField, 
                sortDir);
        }

        model.addAttribute("allLocList", locations);
        model.addAttribute("id", id);
        model.addAttribute("phoneNumber", phoneNumber);
        model.addAttribute("faxNumber", faxNumber);
        model.addAttribute("locationName", locationName);
        model.addAttribute("streetAddress", streetAddress);
        model.addAttribute("city", city);
        model.addAttribute("state", state);
        model.addAttribute("zipCode", zipCode);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "locationList";
    }
}
