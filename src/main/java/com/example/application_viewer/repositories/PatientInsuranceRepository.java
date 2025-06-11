package com.example.application_viewer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.application_viewer.models.PatientInsurance;

@Repository
public interface PatientInsuranceRepository extends 
    JpaRepository<PatientInsurance, Long>, 
    JpaSpecificationExecutor<PatientInsurance> {
    
}
