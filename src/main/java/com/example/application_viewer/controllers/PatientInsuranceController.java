package com.example.application_viewer.controllers;

import java.util.List;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.application_viewer.components.ClaimType;
import com.example.application_viewer.components.HealthSavingsAccount;
import com.example.application_viewer.components.HierarchyType;
import com.example.application_viewer.components.MspReason;
import com.example.application_viewer.components.SexType;
import com.example.application_viewer.models.Patient;
import com.example.application_viewer.models.PatientInsurance;
import com.example.application_viewer.services.PatientInsuranceService;

@Controller
public class PatientInsuranceController {
    
    @Autowired private PatientInsuranceService patientInsuranceService;

    @GetMapping("/patient_insurance_list")
    public String searchPatientInsurances(
        @RequestParam(required = false) Long id,
        @RequestParam(required = false) Patient patient,
        @RequestParam(required = false) Boolean active,
        @RequestParam(required = false) String insuranceName,
        @RequestParam(required = false) String contractCode,
        @RequestParam(required = false) String policyNumber,
        @RequestParam(required = false) String groupNumber,
        @RequestParam(required = false) String planName,
        @RequestParam(required = false) String payId,
        @RequestParam(required = false) HierarchyType hierarchyType,
        @RequestParam(required = false) Double coveragePercent,
        @RequestParam(required = false) LocalDate beginDate,
        @RequestParam(required = false) LocalDate endDate,
        @RequestParam(required = false) String insuredFirstName,
        @RequestParam(required = false) String insuredLastName,
        @RequestParam(required = false) LocalDate insuredDob,
        @RequestParam(required = false) String insurecLastFourSsn,
        @RequestParam(required = false) SexType insuredSex,
        @RequestParam(required = false) ClaimType claimType,
        @RequestParam(required = false) LocalDate claimDate,
        @RequestParam(required = false) String localState,
        @RequestParam(required = false) String alternateBillingPlan,
        @RequestParam(required = false) String payLaterPlanNumber,
        @RequestParam(required = false) MspReason mspReason,
        @RequestParam(required = false) Double yearlyDeductible,
        @RequestParam(required = false) HealthSavingsAccount healthSavingsAccount,
        @RequestParam(required = false) String notes,
        @RequestParam(required = false, defaultValue = "id") String sortField,
        @RequestParam(required = false, defaultValue = "asc") String sortDir,
        Model model) {

        List<PatientInsurance> insurances = patientInsuranceService.searchAndSortPatientInsurances(
            id, 
            patient, 
            active, 
            insuranceName, 
            contractCode, 
            policyNumber, 
            groupNumber, 
            planName, 
            payId, 
            hierarchyType, 
            coveragePercent, 
            beginDate, 
            endDate, 
            insuredFirstName, 
            insuredLastName, 
            insuredDob, 
            insurecLastFourSsn, 
            insuredSex, 
            claimType, 
            claimDate, 
            localState, 
            alternateBillingPlan, 
            payLaterPlanNumber, 
            mspReason, 
            yearlyDeductible, 
            healthSavingsAccount, 
            notes, 
            sortField, 
            sortDir);

        model.addAttribute("id", id);
        model.addAttribute("patient", patient);
        model.addAttribute("active", active);
        model.addAttribute("insuranceName", insuranceName);
        model.addAttribute("contractCode", contractCode);
        model.addAttribute("policyNumber", policyNumber);
        model.addAttribute("groupNumber", groupNumber);
        model.addAttribute("planName", planName);
        model.addAttribute("payId", payId);
        model.addAttribute("hierarchyType", hierarchyType);
        model.addAttribute("coveragePercent", coveragePercent);
        model.addAttribute("beginDate", beginDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("insuredFirstName", insuredFirstName);
        model.addAttribute("insuredLastName", insuredLastName);
        model.addAttribute("insuredDob", insuredDob);
        model.addAttribute("insurecLastFourSsn", insurecLastFourSsn);
        model.addAttribute("insuredSex", insuredSex);
        model.addAttribute("claimType", claimType);
        model.addAttribute("claimDate", claimDate);
        model.addAttribute("localState", localState);
        model.addAttribute("alternateBillingPlan", alternateBillingPlan);
        model.addAttribute("payLaterPlanNumber", payLaterPlanNumber);
        model.addAttribute("mspReason", mspReason);
        model.addAttribute("yearlyDeductible", yearlyDeductible);
        model.addAttribute("healthSavingsAccount", healthSavingsAccount);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "patientInsuranceList";
    }
    
}
