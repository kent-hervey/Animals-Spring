package com.example.restservice.controllers;

import com.example.restservice.services.AnimalService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.restservice.models.Animal;

@RequestMapping("/api")
@RestController

public class AnimalController {


    private AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/animals")
    public ResponseEntity<List<Animal>> getAnimals() {

        List<Animal> animals = new ArrayList<>();

        animals = animalService.findAll();

        //String stringy = animalService.printString("Hello World");

        return ResponseEntity.ok(animals);
    }

    @GetMapping("/animals/{id}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable Long id) {
        Animal animal = animalService.findById(id);
        System.out.println();
        return ResponseEntity.ok(animal);
    }
}
