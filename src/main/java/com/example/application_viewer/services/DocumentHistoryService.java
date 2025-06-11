// /*
//  * Name: Mitchell Thompson
//  * File: DocumentHistoryService.java
//  * Project: Data Viewer Application
//  */

// package com.example.application_viewer.services;

// import java.time.LocalDateTime;
// import java.util.List;
// import java.util.Optional;

// import org.springframework.data.domain.Sort;
// import org.springframework.data.jpa.domain.Specification;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.stereotype.Service;

// import com.example.application_viewer.models.DocumentHistory;
// import com.example.application_viewer.repositories.DocumentHistoryRepository;

// @Service
// public class DocumentHistoryService {

//     private final DocumentHistoryRepository historyRepository;

//     public DocumentHistoryService(DocumentHistoryRepository historyRepository) {
//         this.historyRepository = historyRepository;
//     }

//     public void recordAction(String fileName, String action) {
//         String username = SecurityContextHolder.getContext().getAuthentication().getName();
//         // DocumentHistory history = new DocumentHistory(username, action, fileName, LocalDateTime.now());
//         // historyRepository.save(history);
//         LocalDateTime threshold = LocalDateTime.now().minusSeconds(1);
//         Optional<DocumentHistory> recent = historyRepository.findRecentByUsernameFileNameAction(username, fileName, action, threshold);

//         if (recent.isEmpty()) {
//             DocumentHistory history = new DocumentHistory(username, action, fileName, LocalDateTime.now());
//             historyRepository.save(history);
//         }
//     }

//     public List<DocumentHistory> getUserHistory(String username) {
//         return historyRepository.findByUsernameOrderByTimestampDesc(username);
//     }

//     public List<DocumentHistory> listAllHistory() {
//         return historyRepository.findAll();
//     }

//     public List<DocumentHistory> searchAndSortDocumentHistory(
//         Long id,
//         String username,
//         String fileName,
//         String action,
//         LocalDateTime startTimestamp,
//         LocalDateTime endTimestamp,
//         String sortField,
//         String sortDir) {

//         Specification<DocumentHistory> spec = (root, query, cb) -> cb.conjunction();

//         if (id != null) {
//             spec = spec.and((root, query, cb) -> cb.equal(root.get("id"), id));
//         }

//         if (username != null && !username.isEmpty()) {
//             spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("username")), "%" + username.toLowerCase() + "%"));
//         }

//         if (fileName != null && !fileName.isEmpty()) {
//             spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("fileName")), "%" + fileName.toLowerCase() + "%"));
//         }

//         if (action != null && !action.isEmpty()) {
//             spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("action")), "%" + action.toLowerCase() + "%"));
//         }

//         if (startTimestamp != null && endTimestamp != null) {
//             spec = spec.and((root, query, cb) -> cb.between(root.get("timestamp"), startTimestamp, endTimestamp));
//         } else if (startTimestamp != null) {
//             spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("timestamp"), startTimestamp));
//         } else if (endTimestamp != null) {
//             spec = spec.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("timestamp"), endTimestamp));
//         }

//         Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortField);

//         return historyRepository.findAll(spec, sort);
//     }
// }
