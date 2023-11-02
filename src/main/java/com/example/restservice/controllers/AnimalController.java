package com.example.restservice.controllers;

import com.example.restservice.services.AnimalService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        return ResponseEntity.ok(animals);
    }

    @PostMapping("/animals")
    public ResponseEntity<Animal> createAnimal(@RequestBody Animal animal) {
        Animal createdAnimal = animalService.save(animal);
        return ResponseEntity.ok(createdAnimal);
    }

//    or
//    @PostMapping("/animals")
//    public ResponseEntity<Animal> createAnimal(@RequestBody Animal newAnimal) {
//        try {
//            // Call the service to create the new animal
//            Animal createdAnimal = animalService.createAnimal(newAnimal);
//
//            // Return a response with the created animal and a 201 Created status
//            return ResponseEntity.status(HttpStatus.CREATED).body(createdAnimal);
//        } catch (Exception e) {
//            // If there's an issue with the request or creating the animal, return a 400 Bad Request
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//    }



    @GetMapping("/animals/{id}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable Long id) {
        Animal animal = animalService.findById(id);
        System.out.println();
        return ResponseEntity.ok(animal);
    }

    @PutMapping("/animals/{id}")
    public ResponseEntity<Animal> updateAnimal(@PathVariable Long id, @RequestBody Animal updatedAnimal) {
        Animal existingAnimal = animalService.findById(id);
        System.out.println("existing animal " + existingAnimal + " and its id is:  " + existingAnimal.getId());

        if (existingAnimal.getId() == -1L) {
            System.out.println("Animal with ID " + id + " not found");
            // Handle the case where the animal with the given ID doesn't exist
            return ResponseEntity.notFound().build();
        }
        existingAnimal.setId(id);
        existingAnimal.setName(updatedAnimal.getName());
        existingAnimal.setWeight(updatedAnimal.getWeight());
        existingAnimal.setAge(updatedAnimal.getAge());

        // Save the updated animal
        updatedAnimal = animalService.upate(existingAnimal);

        return ResponseEntity.ok(updatedAnimal);
    }


//    @GetMapping("/animals/{id}")
//    public ResponseEntity<Animal> getAnimalById(@PathVariable Long id) {
//        Animal animal = animalService.findById(id);
//        System.out.println();
//        return ResponseEntity.ok(animal);
//    }

    @DeleteMapping("/animals/{id}")
    public ResponseEntity<Animal> deleteAnimal(@PathVariable Long id) {
        System.out.println("received id is: " + id);
        Animal existingAnimal = animalService.findById(id);
        System.out.println("found animal is: " + existingAnimal.getName());
        if (existingAnimal.getId() == -1L) {
            System.out.println("Animal with ID " + id + " not found");
            // Handle the case where the animal with the given ID doesn't exist
            return ResponseEntity.notFound().build();
        }
        System.out.println("deleting animal: " + existingAnimal.getName());
        animalService.delete(existingAnimal);
        return ResponseEntity.ok(existingAnimal);
    }


//possible method to save list of animals to json file
//    @PostMapping("/animals")


    @PostMapping("/animals/save-to-file")
    public ResponseEntity<String> saveAnimalsToFile() {
        try {
            // Call your saveAnimalsToFile() method here

            // Return a success response
            return ResponseEntity.ok("Animals saved to file.");
        } catch (Exception e) {
            // Handle any exceptions and return an appropriate response, e.g., 500 Internal Server Error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save animals to file.");
        }
    }











}
