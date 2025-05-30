package com.example.application_viewer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.application_viewer.models.User;
import com.example.application_viewer.services.UserService;

/*
 * A controller class that manages request relating to Users, the individuals that
 * log onto the application to view patient data.
 */
@Controller
public class UserController {
    
    @Autowired private UserService userService;

    @GetMapping("/add_user")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);

        return "register";
    }

    @PostMapping("/save_user")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.save(user);

        return "redirect:/login?registered";
    }

    @GetMapping("delete_user/{id}")
    public String deleteByID(@PathVariable(value = "id") long id) {
        userService.deleteByID(id);
        return "redirect:/";
    }

}
