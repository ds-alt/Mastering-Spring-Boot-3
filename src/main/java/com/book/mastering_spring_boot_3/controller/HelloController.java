package com.book.mastering_spring_boot_3.controller;

import com.book.mastering_spring_boot_3.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // Општа префиксна патека
public class HelloController {

    @Value("${app.message:Default message}")
    private String message;

    // Basic hello endpoint
    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from Mastering Spring Boot 3!");
    }

    // Config value from application.properties
    @GetMapping("/config")
    public ResponseEntity<String> getMessage() {
        return ResponseEntity.ok(message);
    }

    // Query param example
    @GetMapping("/greet")
    public ResponseEntity<String> greetUser(@RequestParam String name) {
        return ResponseEntity.ok("Hello, " + name + "!");
    }

    // Path variable example
    @GetMapping("/greet/{name}")
    public ResponseEntity<String> greetUserPath(@PathVariable String name) {
        return ResponseEntity.ok("Hi there, " + name + "!");
    }

    // Path + query param combined
    @GetMapping("/info/{id}")
    public ResponseEntity<String> showInfo(
            @PathVariable int id,
            @RequestParam String details) {
        return ResponseEntity.ok("ID: " + id + ", Details: " + details);
    }

    // JSON POST request with request body
    @PostMapping("/user")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        if (user.getName() == null || user.getAge() == 0) {
            return ResponseEntity.badRequest().body("Invalid user data");
        }
        return ResponseEntity.ok("User created: " + user.getName() + ", age: " + user.getAge());
    }
}
