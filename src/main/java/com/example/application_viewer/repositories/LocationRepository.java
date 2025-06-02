package com.example.application_viewer.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.application_viewer.models.Location;

public interface LocationRepository extends JpaRepository<Location, String>, JpaSpecificationExecutor<Location> {
    @Query("SELECT l FROM Location l WHERE " +
           "(:id IS NULL OR LOWER(l.id) LIKE LOWER(CONCAT('%', :id, '%'))) AND " +
           "(:phoneNumber IS NULL OR LOWER(l.phoneNumber) LIKE LOWER(CONCAT('%', :phoneNumber, '%'))) AND " +
           "(:faxNumber IS NULL OR LOWER(l.faxNumber) LIKE LOWER(CONCAT('%', :faxNumber, '%'))) AND " +
           "(:locationName IS NULL OR LOWER(l.locationName) LIKE LOWER(CONCAT('%', :locationName, '%'))) AND " +
           "(:streetAddress IS NULL OR LOWER(l.streetAddress) LIKE LOWER(CONCAT('%', :streetAddress, '%'))) AND " +
           "(:city IS NULL OR LOWER(l.city) LIKE LOWER(CONCAT('%', :city, '%'))) AND " +
           "(:state IS NULL OR STR(l.state) LIKE CONCAT('%', :state, '%')) AND " +
           "(:zipCode IS NULL OR l.zipCode LIKE CONCAT('%', :zipCode, '%'))")
    List<Location> searchLocations(@Param("id") String id,
                                 @Param("phoneNumber") String phoneNumber,
                                 @Param("faxNumber") String faxNumber,
                                 @Param("locationName") String locationName,
                                 @Param("streetAddress") String streetAddress,
                                 @Param("city") String city,
                                 @Param("state") String state,
                                 @Param("zipCode") String zipCode);
}
