package com.book.mastering_spring_boot_3.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.book.mastering_spring_boot_3.model.User;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from Mastering Spring Boot 3!";
    }

    // If you want it not to break when there is no value, set the default:
    @Value("${app.message:Default message}")
    private String message;

    @GetMapping("/config")
    public String getMessage() {
        return message;
    }

    @GetMapping("/greet")
    public String greetUser(@RequestParam String name) {
        return "Hello, " + name + "!";
    }

    @GetMapping("/greet/{name}")
    public String greetUserPath(@PathVariable String name) {
        return "Hi there, " + name + "!";
    }

    @GetMapping("/info/{id}")
    public String showInfo(@PathVariable int id, @RequestParam String details) {
        return "ID: " + id + ", Details: " + details;
    }

    @PostMapping("/user")
    public String createUser(@RequestBody User user) {
        return "User created: " + user.getName() + ", age: " + user.getAge();
    }

}
