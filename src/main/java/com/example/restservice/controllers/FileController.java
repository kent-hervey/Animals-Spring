package com.example.restservice.controllers;

import com.example.restservice.services.AnimalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api")
@RestController

public class FileController {
    private final AnimalService animalService;

    public FileController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping("save")
    public ResponseEntity<String> saveAnimals() throws Exception {
        try {
            String returnFromService = animalService.persistChanges();
            log.info("Data coming back from service:  " + returnFromService);

            // Return a success response
            return ResponseEntity.ok("All changes persisted, " + returnFromService);
        } catch (Exception e) {
            // Handle any exceptions and return an appropriate response, e.g., 500 Internal Server Error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save animals to file.");
        }
    }
}
