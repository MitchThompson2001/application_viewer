package com.example.application_viewer.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.application_viewer.models.DocumentHistory;
import com.example.application_viewer.repositories.DocumentHistoryRepository;

@Service
public class DocumentHistoryService {

    private final DocumentHistoryRepository historyRepository;

    public DocumentHistoryService(DocumentHistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public void recordAction(String fileName, String action) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        // DocumentHistory history = new DocumentHistory(username, action, fileName, LocalDateTime.now());
        // historyRepository.save(history);
        LocalDateTime threshold = LocalDateTime.now().minusSeconds(1);
        Optional<DocumentHistory> recent = historyRepository.findRecentByUsernameFileNameAction(username, fileName, action, threshold);

        if (recent.isEmpty()) {
            DocumentHistory history = new DocumentHistory(username, action, fileName, LocalDateTime.now());
            historyRepository.save(history);
        }
    }

    public List<DocumentHistory> getUserHistory(String username) {
        return historyRepository.findByUsernameOrderByTimestampDesc(username);
    }
}
