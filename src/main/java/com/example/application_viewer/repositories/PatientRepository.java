/*
 * Name: Mitchell Thompson
 * File: PatientRepository.java
 * Project: Data Viewer Application
 */

package com.example.application_viewer.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.application_viewer.models.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>, JpaSpecificationExecutor<Patient> {
    @Query(
       "SELECT p FROM Patient p WHERE " +
       "(:id IS NULL OR p.id = :id) AND " +
       "(:firstName IS NULL OR LOWER(p.firstName) LIKE LOWER(CONCAT('%', :firstName, '%'))) AND " +
       "(:lastName IS NULL OR LOWER(p.lastName) LIKE LOWER(CONCAT('%', :lastName, '%'))) AND " +
       "(:email IS NULL OR LOWER(p.email) LIKE LOWER(CONCAT('%', :email, '%'))) AND " +
       "(:dob IS NULL OR STR(p.dob) LIKE CONCAT('%', :dob, '%')) AND " +
       "(:phone IS NULL OR p.phone LIKE CONCAT('%', :phone, '%'))")
    List<Patient> searchPatients(
       @Param("id") Long id, 
       @Param("firstName") String firstName,
       @Param("lastName") String lastName,
       @Param("email") String email,
       @Param("dob") String dob,
       @Param("phone") String phone);
}
