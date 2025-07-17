package com.book.mastering_spring_boot_3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String showAdminDashboard() {
        return "admin-dashboard";
    }
}
