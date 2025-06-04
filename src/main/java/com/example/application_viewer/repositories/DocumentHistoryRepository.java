/*
 * Name: Mitchell Thompson
 * File: DocumentHistoryRepository.java
 * Project: Data Viewer Application
 */

package com.example.application_viewer.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.application_viewer.models.DocumentHistory;

@Repository
public interface DocumentHistoryRepository extends JpaRepository<DocumentHistory, Long>, JpaSpecificationExecutor<DocumentHistory> {

    List<DocumentHistory> findByUsernameOrderByTimestampDesc(String username);

    @Query("SELECT dh FROM DocumentHistory dh WHERE dh.username = :username AND dh.fileName = :fileName AND dh.action = :action AND dh.timestamp > :threshold")
    Optional<DocumentHistory> findRecentByUsernameFileNameAction(
            @Param("username") String username,
            @Param("fileName") String fileName,
            @Param("action") String action,
            @Param("threshold") LocalDateTime threshold);

    @Query(
       "SELECT dh FROM DocumentHistory dh WHERE " +
       "(:id IS NULL OR dh.id = :id) AND " +
       "(:username IS NULL OR dh.username = :username) AND" +
       "(:fileName IS NULL OR LOWER(dh.fileName) LIKE LOWER(CONCAT('%', :fileName, '%'))) AND " +
       "(:action IS NULL OR dh.action = :action) AND" +
       "(:startTimestamp IS NULL OR dh.timestamp >= :startTimestamp) AND " +
       "(:endTimestamp IS NULL OR dh.timestamp <= :endTimestamp)")
    List<DocumentHistory> searchDocumentHistory(
      @Param("id") Long id,
      @Param("username") String username,
      @Param("fileName") String fileName,
      @Param("action") String action,
      @Param("startTimestamp") LocalDateTime startTimestamp,
      @Param("endTimestamp") LocalDateTime endTimestamp);
}
