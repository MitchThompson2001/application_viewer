package com.example.application_viewer.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.application_viewer.models.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long>, JpaSpecificationExecutor<Location> {
    @Query("SELECT l FROM Location l WHERE " +
           "(:phoneNumber IS NULL OR LOWER(l.phoneNumber) LIKE LOWER(CONCAT('%', :phoneNumber, '%'))) AND " +
           "(:faxNumber IS NULL OR LOWER(l.faxNumber) LIKE LOWER(CONCAT('%', :faxNumber, '%'))) AND " +
           "(:locationName IS NULL OR LOWER(l.locationName) LIKE LOWER(CONCAT('%', :locationName, '%'))) AND " +
           "(:streetAddress IS NULL OR LOWER(l.streetAddress) LIKE LOWER(CONCAT('%', :streetAddress, '%'))) AND " +
           "(:city IS NULL OR LOWER(l.city) LIKE LOWER(CONCAT('%', :city, '%'))) AND " +
           "(:state IS NULL OR STR(l.state) LIKE CONCAT('%', :state, '%')) AND " +
           "(:zipCode IS NULL OR l.zipCode LIKE CONCAT('%', :zipCode, '%'))")
    List<Location> searchLocations(@Param("phoneNumber") String phoneNumber,
                                 @Param("faxNumber") String faxNumber,
                                 @Param("locationName") String locationName,
                                 @Param("streetAddress") String streetAddress,
                                 @Param("city") String city,
                                 @Param("state") String state,
                                 @Param("zipCode") String zipCode);
}
