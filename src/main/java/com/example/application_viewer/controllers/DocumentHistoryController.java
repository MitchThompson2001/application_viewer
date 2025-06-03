/*
 * Name: Mitchell Thompson
 * File: DocumentHistoryController.java
 * Project: Data Viewer Application
 */ 

package com.example.application_viewer.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.application_viewer.models.DocumentHistory;
import com.example.application_viewer.services.DocumentHistoryService;

@Controller
public class DocumentHistoryController {
    
    @Autowired DocumentHistoryService documentHistoryService;

    /*
     * Allows user to enter specifications for finding an entry in the Document
     * History repository
     */
    @GetMapping("history_list")
    public String searchHistory(
        @RequestParam(required = false) Long id,
        @RequestParam(required = false) String username,
        @RequestParam(required = false) String fileName,
        @RequestParam(required = false) String action,
        @RequestParam(required = false) @DateTimeFormat(pattern = "MM-dd-yyyy HH:mm:ss") LocalDateTime timestamp,
        @RequestParam(required = false, defaultValue = "id") String sortField,
        @RequestParam(required = false, defaultValue = "asc") String sortDir,
        Model model) {

        boolean hasSearchInput = Stream.of(
            id, 
            username, 
            fileName, 
            action, 
            timestamp).anyMatch(Objects::nonNull);

        List<DocumentHistory> history;
        if (hasSearchInput) {
            history = documentHistoryService.searchAndSortDocumentHistory(
                id,
                username, 
                fileName,
                action, 
                timestamp,
                sortField,
                sortDir);
        } else {
            history = documentHistoryService.searchAndSortDocumentHistory(
                null,
                null, 
                null,
                null, 
                null,
                sortField,
                sortDir);
        }

        model.addAttribute("history", history);
        model.addAttribute("id", id);
        model.addAttribute("username", username);
        model.addAttribute("fileName", fileName);
        model.addAttribute("action", action);
        model.addAttribute("timestamp", timestamp);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "historyList";
    }
}
