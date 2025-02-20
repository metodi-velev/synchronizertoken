package com.example.synchronizertoken.controller;

import com.example.synchronizertoken.model.FormData;
import com.example.synchronizertoken.service.TokenService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

@RequiredArgsConstructor
@Controller
public class FormController {

    private final TokenService tokenService;

    // Display the form
    @GetMapping("/form")
    public String showForm(Model model, HttpSession session) {
        // Generate a new token and store it in the database
        String sessionId = session.getId();
        String token = tokenService.generateToken(sessionId);

        // Add the token to the model for rendering in the form
        model.addAttribute("formData", new FormData());
        model.addAttribute("token", token);

        return "form";
    }

    // Handle form submission
    @PostMapping("/submit")
    public String submitForm(@ModelAttribute FormData formData, @RequestParam String token, HttpSession session, SessionStatus sessionStatus) {
        // Retrieve the session ID
        String sessionId = session.getId();

        // Validate the token
        if (tokenService.isValidToken(sessionId, token)) {
            // Process the form data (e.g., save to database)
            System.out.println("Form submitted with data: " + formData.getInputData());

            // Option 1. Mark the token as used after successful submission
            tokenService.markTokenAsUsed(sessionId);

            // Option 2. Invalidate the token after successful submission
            //tokenService.deleteToken(sessionId);

            return "success";
        } else {
            // Token is invalid, return an error
            return "error";
        }
    }
}