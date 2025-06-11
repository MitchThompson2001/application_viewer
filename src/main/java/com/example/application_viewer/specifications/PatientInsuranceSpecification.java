package com.example.application_viewer.specifications;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.example.application_viewer.models.PatientInsurance;
import com.example.application_viewer.components.HierarchyType;
import com.example.application_viewer.components.SexType;
import com.example.application_viewer.components.ClaimType;
import com.example.application_viewer.components.HealthSavingsAccount;
import com.example.application_viewer.components.MspReason;
import com.example.application_viewer.models.Patient;

public class PatientInsuranceSpecification {

    public static Specification<PatientInsurance> filterBy(
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
        String notes) {

        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (id != null) predicates.add(cb.equal(root.get("id"), id));
            if (patient != null) predicates.add(cb.equal(root.get("patient"), patient));
            if (active != null) predicates.add(cb.equal(root.get("active"), active));
            if (insuranceName != null) predicates.add(cb.like(cb.lower(root.get("insuranceName")), "%" + insuranceName.toLowerCase() + "%"));
            if (contractCode != null) predicates.add(cb.equal(root.get("contractCode"), contractCode));
            if (policyNumber != null) predicates.add(cb.equal(root.get("policyNumber"), policyNumber));
            if (groupNumber != null) predicates.add(cb.equal(root.get("groupNumber"), groupNumber));
            if (planName != null) predicates.add(cb.like(cb.lower(root.get("planName")), "%" + planName.toLowerCase() + "%"));
            if (payId != null) predicates.add(cb.equal(root.get("payId"), payId));
            if (hierarchyType != null) predicates.add(cb.equal(root.get("hierarchyType"), hierarchyType));
            if (coveragePercent != null) predicates.add(cb.equal(root.get("coveragePercent"), coveragePercent));
            if (beginDate != null) predicates.add(cb.equal(root.get("beginDate"), beginDate));
            if (endDate != null) predicates.add(cb.equal(root.get("endDate"), endDate));
            if (insuredFirstName != null) predicates.add(cb.like(cb.lower(root.get("insuredFirstName")), "%" + insuredFirstName.toLowerCase() + "%"));
            if (insuredLastName != null) predicates.add(cb.like(cb.lower(root.get("insuredLastName")), "%" + insuredLastName.toLowerCase() + "%"));
            if (insuredDob != null) predicates.add(cb.equal(root.get("insuredDob"), insuredDob));
            if (insurecLastFourSsn != null) predicates.add(cb.equal(root.get("insurecLastFourSsn"), insurecLastFourSsn));
            if (insuredSex != null) predicates.add(cb.equal(root.get("insuredSex"), insuredSex));
            if (claimType != null) predicates.add(cb.equal(root.get("claimType"), claimType));
            if (claimDate != null) predicates.add(cb.equal(root.get("claimDate"), claimDate));
            if (localState != null) predicates.add(cb.like(cb.lower(root.get("localState")), "%" + localState.toLowerCase() + "%"));
            if (alternateBillingPlan != null) predicates.add(cb.like(cb.lower(root.get("alternateBillingPlan")), "%" + alternateBillingPlan.toLowerCase() + "%"));
            if (payLaterPlanNumber != null) predicates.add(cb.equal(root.get("payLaterPlanNumber"), payLaterPlanNumber));
            if (mspReason != null) predicates.add(cb.equal(root.get("mspReason"), mspReason));
            if (yearlyDeductible != null) predicates.add(cb.equal(root.get("yearlyDeductible"), yearlyDeductible));
            if (healthSavingsAccount != null) predicates.add(cb.equal(root.get("healthSavingsAccount"), healthSavingsAccount));
            if (notes != null) predicates.add(cb.like(cb.lower(root.get("notes")), "%" + notes.toLowerCase() + "%"));

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
