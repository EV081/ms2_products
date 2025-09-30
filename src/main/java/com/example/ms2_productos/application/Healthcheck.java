package com.example.ms2_productos.application;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class Healthcheck {

    @GetMapping
    public ResponseEntity<String> check() {
        return ResponseEntity.ok("OK");
    }
}

