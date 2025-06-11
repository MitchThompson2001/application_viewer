package com.example.application_viewer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.application_viewer.components.AddressType;
import com.example.application_viewer.components.ResidenceType;
import com.example.application_viewer.models.Patient;
import com.example.application_viewer.models.PatientAddress;
import com.example.application_viewer.services.PatientAddressService;

@Controller
public class PatientAddressController {
    @Autowired private PatientAddressService patientAddressService;

    @GetMapping("patient_address_list")
    public String searchPatientAddress(
        @RequestParam(required = false) Long id,
        @RequestParam(required = false) Patient patient,
        @RequestParam(required = false) Boolean active,
        @RequestParam(required = false) AddressType addressType,
        @RequestParam(required = false) ResidenceType residenceType,
        @RequestParam(required = false) String addressA,
        @RequestParam(required = false) String addressB,
        @RequestParam(required = false) String city,
        @RequestParam(required = false) String state,
        @RequestParam(required = false) String county,
        @RequestParam(required = false) String zipCode,
        @RequestParam(required = false) String country,
        @RequestParam(required = false) String geoCode,
        @RequestParam(required = false) String electricCompany,
        @RequestParam(required = false) String serviceLocNum,
        @RequestParam(required = false, defaultValue = "id") String sortField,
        @RequestParam(required = false, defaultValue = "asc") String sortDir,
        Model model) {

        List<PatientAddress> addresses = patientAddressService.searchAndSortPatientAddresses(
            id,
            patient,
            active,
            addressType,
            residenceType,
            addressA,
            addressB,
            city,
            state,
            county,
            zipCode,
            country,
            geoCode,
            electricCompany,
            serviceLocNum,
            sortField,
            sortDir
        );

        model.addAttribute("allPatAddrList", addresses);
        model.addAttribute("id", id);
        model.addAttribute("patient", patient);
        model.addAttribute("active", active);
        model.addAttribute("addressType", addressType);
        model.addAttribute("residenceType", residenceType);
        model.addAttribute("addressA", addressB);
        model.addAttribute("city", city);
        model.addAttribute("state", state);
        model.addAttribute("county", county);
        model.addAttribute("zipCode", zipCode);
        model.addAttribute("country", country);
        model.addAttribute("geoCode", geoCode);
        model.addAttribute("electricCompany", electricCompany);
        model.addAttribute("serviceLocNum", serviceLocNum);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "patientAddressList";
    }
}
