package com.book.mastering_spring_boot_3.controller;

import com.book.mastering_spring_boot_3.service.AsyncTaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
@RequestMapping("/async")
public class AsyncTaskController {

    private final AsyncTaskService asyncTaskService;

    public AsyncTaskController(AsyncTaskService asyncTaskService) {
        this.asyncTaskService = asyncTaskService;
    }

    @GetMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestParam String to) {
        if (!isValidEmail(to)) {
            return ResponseEntity.badRequest().body("Invalid email address: " + to);
        }

        asyncTaskService.sendTestEmail(to);
        return ResponseEntity.ok("Email task started for: " + to);
    }

    // Utility method to validate email format
    private boolean isValidEmail(String email) {
        // Simple regex for email format
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(emailRegex, email);
    }
}
