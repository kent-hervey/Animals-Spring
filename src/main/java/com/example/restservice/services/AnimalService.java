package com.example.restservice.services;

import com.example.restservice.models.Animal;
import com.example.restservice.repositories.AnimalRepositoryImpl;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.example.restservice.enums.Kind;

@Slf4j
@Service
public class AnimalService  {

    AnimalRepositoryImpl animalRepository = new AnimalRepositoryImpl();

    public List<Animal> findAll() {
        List<Animal> animals;
        animals = animalRepository.findAll();
        log.info("Service class found all animals:  " + animals);
        return animals;
    }

    public Animal findById(Long id) {
        Animal animal;
        animal = animalRepository.findById(id);
        log.info("Service class found animal:  " + animal);
        return animal;
    }

    public Animal addNew(Animal animal) {
        if (Kind.isValidKind(animal.getKind())) {
            throw new IllegalArgumentException("Invalid kind: " + animal.getKind());
        }
        animal.setModifiedDate();
        return animalRepository.addAnimalToList(animal);
    }

    public Animal update(Animal animal) {
        if (Kind.isValidKind(animal.getKind())) {
            throw new IllegalArgumentException("Invalid kind: " + animal.getKind());
        }
        animal.setModifiedDate();
        return animalRepository.updateAnimalInList(animal);
    }

    public long delete(Animal animal) {
        animalRepository.delete(animal);
        return animal.getId();
    }

    public String  persistChanges() {
        System.out.println("Inside AnimalService.persistChanges()....");
        String fromRepository = "No data retrieved from repository.";
        try {
            fromRepository = animalRepository.saveAnimalsToFile();
            log.info("Data coming back from repository:  " + fromRepository);
        } catch (Exception e) {
            log.error("Failed to persist changes to the file", e);
        }
        return "Changes persisted via AnimalService.persistChanges()\n from Repository " + fromRepository;
    }
}
