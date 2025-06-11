package com.example.application_viewer.specifications;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.example.application_viewer.components.AddressType;
import com.example.application_viewer.components.ResidenceType;
import com.example.application_viewer.models.Patient;
import com.example.application_viewer.models.PatientAddress;
import com.example.application_viewer.models.PatientDemographic;

public class PatientAddressSpecification {
    public static Specification<PatientAddress> filterBy(
        Long id,
        Patient patient,
        Boolean active,
        AddressType addressType,
        ResidenceType residenceType,
        String addressA,
        String addressB,
        String city,
        String state,
        String county,
        String zipCode,
        String country,
        String geoCode,
        String electricCompany,
        String serviceLocNum) {
            return (root, query, cb) -> {
                List<Predicate> predicates = new ArrayList<>();

                if (id != null) predicates.add(cb.equal(root.get("id"), id));
                if (patient != null) predicates.add(cb.equal(root.get("patient"), patient));
                if (active != null) predicates.add(cb.equal(root.get("active"), active));
                if (addressType != null) predicates.add(cb.equal(root.get("addressType"), addressType));
                if (residenceType != null) predicates.add(cb.equal(root.get("residenceType"), residenceType));
                if (addressA != null) predicates.add(cb.like(cb.lower(root.get("addressA")), "%" + addressA.toLowerCase() + "%"));
                if (addressB != null) predicates.add(cb.like(cb.lower(root.get("addressB")), "%" + addressB.toLowerCase() + "%"));
                if (city != null) predicates.add(cb.like(cb.lower(root.get("city")), "%" + city.toLowerCase() + "%"));
                if (state != null) predicates.add(cb.like(cb.lower(root.get("state")), "%" + state.toLowerCase() + "%"));
                if (county != null) predicates.add(cb.like(cb.lower(root.get("county")), "%" + county.toLowerCase() + "%"));
                if (zipCode != null) predicates.add(cb.equal(root.get("zipCode"), zipCode));
                if (country != null) predicates.add(cb.like(cb.lower(root.get("country")), "%" + country.toLowerCase() + "%"));
                if (geoCode != null) predicates.add(cb.like(cb.lower(root.get("geoCode")), "%" + geoCode.toLowerCase() + "%"));
                if (electricCompany != null) predicates.add(cb.like(cb.lower(root.get("electricCompany")), "%" + electricCompany.toLowerCase() + "%"));
                if (serviceLocNum != null) predicates.add(cb.like(cb.lower(root.get("serviceLocNum")), "%" + serviceLocNum.toLowerCase() + "%"));
                
                return cb.and(predicates.toArray(new Predicate[0]));
            };
        }
}
