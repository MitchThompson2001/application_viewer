package com.example.application_viewer.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.application_viewer.components.ClaimType;
import com.example.application_viewer.components.HealthSavingsAccount;
import com.example.application_viewer.components.HierarchyType;
import com.example.application_viewer.components.MspReason;
import com.example.application_viewer.components.SexType;
import com.example.application_viewer.models.Patient;
import com.example.application_viewer.models.PatientInsurance;
import com.example.application_viewer.specifications.PatientInsuranceSpecification;
import com.example.application_viewer.repositories.PatientInsuranceRepository;

@Service
public class PatientInsuranceService {

    @Autowired private PatientInsuranceRepository patientInsuranceRepository;

    public List<PatientInsurance> searchAndSortPatientInsurances(
        Long id,
        Patient patient,
        Boolean active,
        String insuranceName,
        String contractCode,
        String policyNumber,
        String groupNumber,
        String planName,
        String payId,
        HierarchyType hierarchyType,
        Double coveragePercent,
        LocalDate beginDate,
        LocalDate endDate,
        String insuredFirstName,
        String insuredLastName,
        LocalDate insuredDob,
        String insurecLastFourSsn,
        SexType insuredSex,
        ClaimType claimType,
        LocalDate claimDate,
        String localState,
        String alternateBillingPlan,
        String payLaterPlanNumber,
        MspReason mspReason,
        Double yearlyDeductible,
        HealthSavingsAccount healthSavingsAccount,
        String notes,
        String sortField,
        String sortDir) {

        Specification<PatientInsurance> spec = PatientInsuranceSpecification.filterBy(
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
            notes);
        
        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortField);

        return patientInsuranceRepository.findAll(spec, sort);
    }
    
}
