/*
 * Name: Mitchell Thompson
 * File: DocumentHistoryController.java
 * Project: Data Viewer Application
 */ 

package com.example.application_viewer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.application_viewer.models.DocumentHistory;
import com.example.application_viewer.services.DocumentHistoryService;

@Controller
public class DocumentHistoryController {
    
    @Autowired DocumentHistoryService documentHistoryService;

    @GetMapping("/history_list")
    public String getHistory(Model model) {
        List<DocumentHistory> history = documentHistoryService.listAllHistory();

        model.addAttribute("history", history);

        return "/historyList";
    }


}
