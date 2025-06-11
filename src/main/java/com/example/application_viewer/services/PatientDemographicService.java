package com.example.application_viewer.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.application_viewer.components.PatientType;
import com.example.application_viewer.components.SexType;
import com.example.application_viewer.models.Patient;
import com.example.application_viewer.models.PatientDemographic;
import com.example.application_viewer.specifications.PatientDemographicSpecification;
import com.example.application_viewer.repositories.PatientDemographicRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PatientDemographicService {
    @Autowired private PatientDemographicRepository patientDemographicRepository;

    public List<PatientDemographic> getAllPatients() {
        return patientDemographicRepository.findAll();
    }

    public List<PatientDemographic> searchAndSortPatientDemographics(
        Long id,
        Patient patient,
        PatientType patientType,
        Boolean active,
        String firstName,
        String lastName,
        String origin,
        String emailA,
        String emailB,
        LocalDate dob,
        String phoneA,
        String phoneB,
        String ssnLastFour,
        String height,
        String weight,
        SexType sex,
        String notes,
        String homeCompanyNum,
        String homeCompanyLoc,
        LocalDate lastUpdated,
        String sortField,
        String sortDir
    ) {
        Specification<PatientDemographic> spec = PatientDemographicSpecification.filterBy(
            id, 
            patient, 
            patientType, 
            active, 
            firstName, 
            lastName, 
            origin, 
            emailA, 
            emailB,
            dob, 
            phoneA, 
            phoneB, 
            ssnLastFour, 
            height, 
            weight, 
            sex, 
            notes,
            homeCompanyNum, 
            homeCompanyLoc, 
            lastUpdated);

        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortField);

        return patientDemographicRepository.findAll(spec, sort);
    }

    // public List<PatientDemographic> searchAndSortPatientDemographics(
    //     Long id,
    //     Long patient,
    //     String patientType,
    //     Boolean active,
    //     String firstName, 
    //     String lastName,
    //     String origin, 
    //     String emailA,
    //     String emailB, 
    //     String dob, 
    //     String phoneA,
    //     String phoneB,
    //     String ssnLastFour,
    //     String height,
    //     String weight,
    //     String sex,
    //     String notes,
    //     String homeCompanyNum,
    //     String homeCompanyLoc,
    //     String lastUpdated, 
    //     String sortField, 
    //     String sortDir) {

    //     Specification<PatientDemographic> spec = (root, query, cb) -> cb.conjunction();

    //     if (id != null) {
    //         spec = spec.and((root, query, cb) -> cb.equal(root.get("id"), id));
    //     }

    //     if (patient != null) {
    //         spec = spec.and((root, query, cb) -> cb.equal(root.get("patientType"), patientType));
    //     }

    //     if (patientType != null && !patientType.isEmpty()) {
    //         spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("firstName")), "%" + firstName.toLowerCase() + "%"));
    //     }

    //     if (active != null) {
    //         spec = spec.and((root, query, cb) -> cb.equal(root.get("active"), active));
    //     }

    //     if (firstName != null && !firstName.isEmpty()) {
    //         spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("firstName")), "%" + firstName.toLowerCase() + "%"));
    //     }

    //     if (lastName != null && !lastName.isEmpty()) {
    //         spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("lastName")), "%" + lastName.toLowerCase() + "%"));
    //     }

    //     if (origin != null && !origin.isEmpty()) {
    //         spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("origin")), "%" + origin.toLowerCase() + "%"));
    //     }

    //     if (emailA != null && !emailA.isEmpty()) {
    //         spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("emailA")), "%" + emailA.toLowerCase() + "%"));
    //     }

    //     if (emailB != null && !emailB.isEmpty()) {
    //         spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("emailB")), "%" + emailB.toLowerCase() + "%"));
    //     }

    //     if (dob != null && !dob.isEmpty()) {
    //         try {
    //             LocalDate dobDate = LocalDate.parse(dob);  // parse the date string to LocalDate
    //             spec = spec.and((root, query, cb) -> cb.equal(root.get("dob"), dobDate));
    //         } catch (DateTimeParseException e) {
    //             // Handle invalid date format if needed, or ignore filter
    //             System.out.println("Invalid dob format: " + dob);
    //         }
    //     }

    //     if (phoneA != null && !phoneA.isEmpty()) {
    //         spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("phoneA")), "%" + phoneA + "%"));
    //     }

    //     if (phoneB != null && !phoneB.isEmpty()) {
    //         spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("phoneB")), "%" + phoneB + "%"));
    //     }

    //     if (ssnLastFour != null && !ssnLastFour.isEmpty()) {
    //         spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("ssnLastFour")), "%" + ssnLastFour + "%"));
    //     }

    //     if (height != null && !height.isEmpty()) {
    //         spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("height")), "%" + height + "%"));
    //     }

    //     if (weight != null && !weight.isEmpty()) {
    //         spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("weight")), "%" + weight + "%"));
    //     }

    //     if (sex != null && !sex.isEmpty()) {
    //         spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("sex")), "%" + sex + "%"));
    //     }

    //     if (notes != null && !notes.isEmpty()) {
    //         spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("notes")), "%" + notes + "%"));
    //     }

    //     if (homeCompanyNum != null && !homeCompanyNum.isEmpty()) {
    //         spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("homeCompanyNum")), "%" + homeCompanyNum + "%"));
    //     }

    //     if (homeCompanyLoc != null && !homeCompanyLoc.isEmpty()) {
    //         spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("homeCompanyLoc")), "%" + homeCompanyLoc + "%"));
    //     }

    //     if (lastUpdated != null && !lastUpdated.isEmpty()) {
    //         spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("lastUpdated")), "%" + lastUpdated + "%"));
    //     }

    //     Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortField);

    //     return patientDemographicRepository.findAll(spec, sort);
    // }

    public PatientDemographic getByID(long id) {
        return patientDemographicRepository.findById(id)
            .orElseThrow(() -> 
            new EntityNotFoundException("Entity not found for ID " + id));
    }
}
