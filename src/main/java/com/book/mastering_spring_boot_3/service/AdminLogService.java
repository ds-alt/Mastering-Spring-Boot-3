package com.book.mastering_spring_boot_3.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AdminLogService {

    private static final Logger logger = LoggerFactory.getLogger(AdminLogService.class);

    public void logAction(String action) {
        logger.info("[ADMIN ACTION] {}", action);
    }

    public void logSystem(String message) {
        logger.info("[SYSTEM] {}", message);
    }
}
