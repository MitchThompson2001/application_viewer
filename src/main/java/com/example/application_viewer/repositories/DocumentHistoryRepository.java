package com.example.application_viewer.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.application_viewer.models.DocumentHistory;

public interface DocumentHistoryRepository extends JpaRepository<DocumentHistory, Long> {

    List<DocumentHistory> findByUsernameOrderByTimestampDesc(String username);

    @Query("SELECT dh FROM DocumentHistory dh WHERE dh.username = :username AND dh.fileName = :fileName AND dh.action = :action AND dh.timestamp > :threshold")
    Optional<DocumentHistory> findRecentByUsernameFileNameAction(
            @Param("username") String username,
            @Param("fileName") String fileName,
            @Param("action") String action,
            @Param("threshold") LocalDateTime threshold);
}
