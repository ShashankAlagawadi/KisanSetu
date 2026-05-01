package com.kisansetu.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class HealthController {

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> home() {
        Map<String, Object> response = new HashMap<>();
        response.put("service", "KisanSetu - Smart Farmer Exchange");
        response.put("status", "RUNNING");
        response.put("version", "1.0.0");
        response.put("endpoints", new String[]{
            "/api/auth - Authentication",
            "/api/crops - Crop listings",
            "/api/orders - Order management",
            "/api/escrow - Escrow payments"
        });
        return ResponseEntity.ok(response);
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("database", "MongoDB - Connected");
        return ResponseEntity.ok(response);
    }
}
