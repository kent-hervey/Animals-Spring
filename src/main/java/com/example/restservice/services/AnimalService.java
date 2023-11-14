package com.example.restservice.services;

import com.example.restservice.models.Animal;
import com.example.restservice.repositories.AnimalRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AnimalService  {

    AnimalRepository animalRepository = new AnimalRepository();


    public List<Animal> findAll() {
        List<Animal> animals;
        animals = animalRepository.findAll();
        log.info("Service class found all animals");
        return animals;
    }

    public Animal findById(Long id) {
        Animal animal;
        animal = animalRepository.findById(id);
        return animal;
    }

    public Animal addNew(Animal animal) {
        return animalRepository.addAnimalToList(animal);
    }

    public Animal update(Animal animal) {
        return animalRepository.updateAnimalInList(animal);
    }

    public void delete(Animal existingAnimal) {
        animalRepository.delete(existingAnimal);
    }

    public String  persistChanges() {
        System.out.println("Inside AnimalService.persistChanges()....");
        String fromRepository = "";
        try {
            fromRepository = animalRepository.saveAnimalsToFile();
            log.info("Data coming back from repository:  " + fromRepository);
        } catch (Exception e) {
            log.error("Failed to persist changes to the file", e);
        }
        return "Changes persisted via AnimalService.persistChanges()\n from Repository " + fromRepository;
    }
}
