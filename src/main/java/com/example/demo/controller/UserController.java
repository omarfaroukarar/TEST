package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        return "registerForm"; // Return registration template
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model) {
        
        // Check if the username or email already exists
        if (userRepository.findByUsername(username) != null) {
            model.addAttribute("error", "Username already exists.");
            return "registerForm"; // Return to registration form with error
        }
        
        if (userRepository.findByEmail(email) != null) {
            model.addAttribute("error", "Email already exists.");
            return "registerForm"; // Return to registration form with error
        }
        
        // Create a new user object
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password); // Note: You should hash passwords in a real application

        // Save the user to the database
        userRepository.save(user);

        return "redirect:/register?success"; // Redirect after successful registration
    }
}
