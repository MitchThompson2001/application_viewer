package com.example.application_viewer.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Authentication authentication) {
        return "home";
    }

    @GetMapping("/")
    public String redirectRoot(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated() &&
            !(authentication instanceof AnonymousAuthenticationToken)) {
            return "redirect:/home";
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken)) {
            // Already logged in — redirect to home
            return "redirect:/home";
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "login";
    }
}