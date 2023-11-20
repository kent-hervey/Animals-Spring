package com.example.restservice.controllers;

import com.example.restservice.dtos.AnimalDTO;
import com.example.restservice.services.AnimalDtoService;
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
    private final AnimalDtoService animalDtoService;

    public AnimalController(AnimalService animalService, AnimalDtoService animalDtoService) {
        this.animalService = animalService;
        this.animalDtoService = animalDtoService;
    }

    @GetMapping("/animals")
    public ResponseEntity<List<Animal>> getAnimals() {
        List<Animal> animals;
        animals = animalService.findAll();
        String names = animals.stream().flatMap(animal -> Stream.of(animal.getName() + ",")).collect(Collectors.joining());
        log.info(">>>>>>\n>>>>>animals: " + names + "\n>>>>>>");
        return ResponseEntity.ok(animals);
    }

    @GetMapping("/animals/{id}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable Long id) {
        Animal animal = animalService.findById(id);
        log.info("Found animal:  " + animal);
        return ResponseEntity.ok(animal);
    }

    //TODO change Food to WithFeedType

    @GetMapping("/animalsFood")
    public ResponseEntity<List<AnimalDTO>> getAnimalWithFeedType() {
        List<AnimalDTO> animalsDTO;
        animalsDTO = animalDtoService.findAllDTO();
        String names = animalsDTO.stream().flatMap(animal -> Stream.of(animal.getName() + ",")).collect(Collectors.joining());
        log.info(">>>>>>\n>>>>>animals: " + names + "\n>>>>>>");
        return ResponseEntity.ok(animalsDTO);
    }

    //TODO change Food to WithFeedType
    @GetMapping("/animalsFood/{id}")
    public ResponseEntity<AnimalDTO> getAnimalWithFeedTypeById(@PathVariable Long id) {
        AnimalDTO animalDTO = animalDtoService.findById(id);
        log.info("Found animal:  " + animalDTO);
        return ResponseEntity.ok(animalDTO);
    }
    @PostMapping("/animals")
    public ResponseEntity<Animal> createAnimal(@RequestBody Animal newAnimal) {
        Animal createdAnimal = animalService.addNew(newAnimal);
        log.info(">>>>>>\n>>>>> successfully created animal: " + createdAnimal.getName() + "\n>>>>>>");
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAnimal);
    }

    @PutMapping("/animals/{id}")
    public ResponseEntity<Animal> updateAnimal(@PathVariable Long id, @RequestBody Animal updatedAnimal) {
        Animal existingAnimal = animalService.findById(id);
        log.info("existing animal " + existingAnimal + " and its id is:  " + existingAnimal.getId());
        if (existingAnimal.getId() == -1L) {
            System.out.println("Animal with ID " + id + " not found");
            return ResponseEntity.notFound().build();
        }
        existingAnimal.setId(id);
        existingAnimal.setName(updatedAnimal.getName());
        existingAnimal.setWeight(updatedAnimal.getWeight());
        existingAnimal.setAge(updatedAnimal.getAge());
        existingAnimal.setKind(updatedAnimal.getKind());
        return ResponseEntity.ok(animalService.update(existingAnimal));
    }

    @DeleteMapping("/animals/{id}")
    public ResponseEntity<Animal> deleteAnimal(@PathVariable Long id) {
        log.info("received id is: " + id);
        Animal existingAnimal = animalService.findById(id);
        log.info("found animal is: " + existingAnimal.getName());
        //TODO change to if (existingAnimal == null)  or some other way to confirm that the animal was not found
        //TODO regardless, thbe below does not work because Spring already provides a 404 response
        if (existingAnimal.getId() == -1L) {
            log.info("Animal with ID " + id + " not found");
            return ResponseEntity.notFound().build();
        }
        log.info("deleting animal: " + existingAnimal.getName());
        animalService.delete(existingAnimal);
        return ResponseEntity.ok(existingAnimal);
    }

    //TODO need a controller method to return list of all valid animal kinds

}
