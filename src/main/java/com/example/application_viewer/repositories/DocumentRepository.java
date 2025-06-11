// /*
//  * Name: Mitchell Thompson
//  * File: DocumentRepository.java
//  * Project: Data Viewer Application
//  */

// package com.example.application_viewer.repositories;

// import java.time.LocalDate;
// import java.util.List;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
// import org.springframework.stereotype.Repository;

// import com.example.application_viewer.models.PatientDocument;



// @Repository
// public interface DocumentRepository extends JpaRepository<PatientDocument, Long>, JpaSpecificationExecutor<PatientDocument> {
//     List<PatientDocument> findByPatientId(Long patientId);
    
//     @Query("SELECT d FROM Document d JOIN FETCH d.patient")
//     List<PatientDocument> findAllWithPatient();

//     @Query(
//        "SELECT d FROM Document d WHERE " +
//        "(:id IS NULL OR d.id = :id) AND " +
//        "(:patientId IS NULL OR d.patient.id = :patientId) AND" +
//        "(:fileName IS NULL OR LOWER(d.fileName) LIKE LOWER(CONCAT('%', :fileName, '%'))) AND " +
//        "(:uploadDate IS NULL OR d.uploadDate = :uploadDate)")
//     List<PatientDocument> searchDocuments(
//       @Param("id") Long id,
//       @Param("patientId") Long patientId,
//       @Param("fileName") String fileName,
//       @Param("uploadDate") LocalDate uploadDate);
// }