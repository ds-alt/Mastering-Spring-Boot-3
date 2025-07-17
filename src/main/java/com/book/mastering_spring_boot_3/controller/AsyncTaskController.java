package com.book.mastering_spring_boot_3.controller;

import com.book.mastering_spring_boot_3.service.AsyncTaskService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/async")
public class AsyncTaskController {

    private final AsyncTaskService asyncTaskService;

    public AsyncTaskController(AsyncTaskService asyncTaskService) {
        this.asyncTaskService = asyncTaskService;
    }

    @GetMapping("/send-email")
    public String sendEmail(@RequestParam String to) {
        asyncTaskService.sendTestEmail(to);
        return "Email task started for " + to;
    }
}
