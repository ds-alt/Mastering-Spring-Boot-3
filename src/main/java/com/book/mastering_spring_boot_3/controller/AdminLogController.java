package com.book.mastering_spring_boot_3.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import org.springframework.core.io.Resource;

@RestController
@RequestMapping("/admin/logs")
public class AdminLogController {

    private static final Logger logger = LoggerFactory.getLogger(AdminLogController.class);
    private static final String LOG_FILE_PATH = "logs/spring.log";

    @GetMapping("/test")
    public String testLog() {
        logger.info("INFO log triggered");
        logger.warn("WARNING log triggered");
        logger.error("ERROR log triggered");
        return "Log test triggered - check console";
    }

    // Return last N lines from the log file
    @GetMapping("/tail")
    public ResponseEntity<List<String>> getLastLogs(@RequestParam(defaultValue = "50") int lines) {
        File file = new File(LOG_FILE_PATH);
        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        List<String> allLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                allLines.add(line);
            }
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }

        int fromIndex = Math.max(allLines.size() - lines, 0);
        return ResponseEntity.ok(allLines.subList(fromIndex, allLines.size()));
    }

    // Optional: Download full log
    @GetMapping("/download")
    public ResponseEntity<Resource> downloadLogFile() throws IOException {
        Path path = new File(LOG_FILE_PATH).toPath();
        Resource resource = new UrlResource(path.toUri());

        return ResponseEntity
                .ok()
                .header("Content-Disposition", "attachment; filename=\"spring.log\"")
                .body(resource);
    }
}
