package com.example.application_viewer.models;

import java.time.LocalDate;

import com.example.application_viewer.components.ClaimType;
import com.example.application_viewer.components.HealthSavingsAccount;
import com.example.application_viewer.components.HierarchyType;
import com.example.application_viewer.components.MspReason;
import com.example.application_viewer.components.SexType;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "patient_insurances")
public class PatientInsurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "active")
    private boolean active;

    @Column(name = "insurance_name")
    private String insuranceName;

    @Column(name = "contract_code")
    private String contractCode;

    @Column(name = "policy_number")
    private String policyNumber;

    @Column(name = "group_number")
    private String groupNumber;

    @Column(name = "plan_name")
    private String planName;

    @Column(name = "pay_id")
    private String payId;

    @Enumerated(EnumType.STRING)
    @Column(name = "hierarchy")
    private HierarchyType hierarchy;

    @Column(name = "coverage_percent")
    private double coveragePercent;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MM-dd-yyyy")
    @Column(name = "begin_date")
    private LocalDate beginDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MM-dd-yyyy")
    @Column(name = "end_date")
    private LocalDate endDate;

    // Insured information only filled if the patient type is not "patient"
    @Column(name = "insured_first_name")
    private String insuredFirstName;

    @Column(name = "insured_last_name")
    private String insuredLastName;

    @Column(name = "insured_dob")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MM-dd-yyyy")
    private LocalDate insuredDob;

    @Column(name = "insured_last_four_ssn")
    private String insuredLastFourSsn;

    @Enumerated(EnumType.STRING)
    @Column(name = "insured_sex")
    private SexType insuredSex;
    // *************************************************************************

    @Enumerated(EnumType.STRING)
    @Column(name = "claim_type")
    private ClaimType claimType;

    @Column(name = "claim_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MM-dd-yyyy")
    private LocalDate claimDate;

    @Column(name = "claim_state")
    private String claimState;

    @Column(name = "local_state")
    private String localState;

    @Column(name = "alternate_billing_plan")
    private String alternateBillingPlan;

    @Column(name = "pay_later_plan_number")
    private String payLaterPlanNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "msp_reason")
    private MspReason mspReason;

    @Column(name = "yearly_deductible")
    private double yearlyDeductible;

    @Enumerated(EnumType.STRING)
    @Column(name = "health_savings_account")
    private HealthSavingsAccount healthSavingsAccount;

    @Column(name = "notes")
    private String notes;

    public PatientInsurance() {}

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }
    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }
    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }
    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }
    public void setPlanName(String planName) {
        this.planName = planName;
    }
    public void setPayId(String payId) {
        this.payId = payId;
    }
    public void setHierarchy(HierarchyType hierarchy) {
        this.hierarchy = hierarchy;
    }
    public void setCoveragePercent(double coveragePercent) {
        this.coveragePercent = coveragePercent;
    }
    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public void setInsuredFirstName(String insuredFirstName) {
        this.insuredFirstName = insuredFirstName;
    }
    public void setInsuredLastName(String insuredLastName) {
        this.insuredLastName = insuredLastName;
    }
    public void setInsuredDob(LocalDate insuredDob) {
        this.insuredDob = insuredDob;
    }
    public void setInsuredLastFourSsn(String insuredLastFourSsn) {
        this.insuredLastFourSsn = insuredLastFourSsn;
    }
    public void setInsuredSex(SexType insuredSex) {
        this.insuredSex = insuredSex;
    }
    public void setClaimType(ClaimType claimType) {
        this.claimType = claimType;
    }
    public void setClaimDate(LocalDate claimDate) {
        this.claimDate = claimDate;
    }
    public void setClaimState(String claimState) {
        this.claimState = claimState;
    }
    public void setLocalState(String localState) {
        this.localState = localState;
    }
    public void setAlternateBillingPlan(String alternateBillingPlan) {
        this.alternateBillingPlan = alternateBillingPlan;
    }
    public void setPayLaterPlanNumber(String payLaterPlanNumber) {
        this.payLaterPlanNumber = payLaterPlanNumber;
    }
    public void setMspReason(MspReason mspReason) {
        this.mspReason = mspReason;
    }
    public void setYearlyDeductible(double yearlyDeductible) {
        this.yearlyDeductible = yearlyDeductible;
    }
    public void setHealthSavingsAccount(HealthSavingsAccount healthSavingsAccount) {
        this.healthSavingsAccount = healthSavingsAccount;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

    public long getId() {
        return this.id;
    }
    public Patient getPatient() {
        return this.patient;
    }
    public boolean isActive() {
        return this.active;
    }
    public String getInsuranceName() {
        return this.insuranceName;
    }
    public String getContractCode() {
        return this.contractCode;
    }
    public String getPolicyNumber() {
        return this.policyNumber;
    }
    public String getGroupNumber() {
        return this.groupNumber;
    }
    public String getPlanName() {
        return this.planName;
    }
    public String getPayId() {
        return this.payId;
    }
    public HierarchyType getHierarchy() {
        return this.hierarchy;
    }
    public double getCoveragePercent() {
        return this.coveragePercent;
    }
    public LocalDate getBeginDate() {
        return this.beginDate;
    }
    public LocalDate getEndDate() {
        return this.endDate;
    }
    public String getInsuredFirstName() {
        return this.insuredFirstName;
    }
    public String getInsuredLastName() {
        return this.insuredLastName;
    }
    public LocalDate getInsuredDob() {
        return this.insuredDob;
    }
    public String getInsuredLastFourSsn() {
        return this.insuredLastFourSsn;
    }
    public SexType getInsuredSex() {
        return this.insuredSex;
    }
    public ClaimType getClaimType() {
        return this.claimType;
    }
    public LocalDate getClaimDate() {
        return this.claimDate;
    }
    public String getClaimState() {
        return this.claimState;
    }
    public String getLocalState() {
        return this.localState;
    }
    public String getAlternateBillingPlan() {
        return this.alternateBillingPlan;
    }
    public String getPayLaterPlanNumber() {
        return this.payLaterPlanNumber;
    }
    public MspReason getMspReason() {
        return this.mspReason;
    }
    public double getYearlyDeductible() {
        return this.yearlyDeductible;
    }
    public HealthSavingsAccount getHealthSavingsAccount() {
        return this.healthSavingsAccount;
    }
    public String getNotes() {
        return this.notes;
    }
}
