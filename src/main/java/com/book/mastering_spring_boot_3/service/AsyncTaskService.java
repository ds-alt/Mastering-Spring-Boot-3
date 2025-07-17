package com.book.mastering_spring_boot_3.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncTaskService {

    @Async
    public void sendTestEmail(String recipient) {
        System.out.println("🔵 Sending email to " + recipient);
        try {
            Thread.sleep(3000); // симулација на доцнење
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("✅ Email sent to " + recipient);
    }
}
