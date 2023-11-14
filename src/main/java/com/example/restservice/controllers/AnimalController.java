package com.example.restservice.controllers;

import com.example.restservice.services.AnimalService;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RequestMapping("/api")
@RestController

public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/animals")
    public ResponseEntity<List<Animal>> getAnimals() {

        List<Animal> animals;
        animals = animalService.findAll();
        //user Stream to print all names of animals
        String names = animals.stream().flatMap(animal -> Stream.of(animal.getName() + ",")).collect(Collectors.joining());
        log.info(">>>>>>\n>>>>>animals: " + names + "\n>>>>>>");
        return ResponseEntity.ok(animals);
    }

    @PostMapping("/animals")
    public ResponseEntity<Animal> createAnimal(@RequestBody Animal newAnimal) {
        // Call the service to create the new animal
        Animal createdAnimal = animalService.addNew(newAnimal);
        log.info(">>>>>>\n>>>>> successfully created animal: " + createdAnimal.getName() + "\n>>>>>>");
        // Return a response with the created animal and a 201 Created status
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAnimal);
    }

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
        updatedAnimal = animalService.update(existingAnimal);

        return ResponseEntity.ok(updatedAnimal);
    }

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
}
