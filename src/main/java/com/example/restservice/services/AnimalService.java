package com.example.restservice.services;

import com.example.restservice.models.Animal;
import com.example.restservice.repositories.AnimalRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class AnimalService  {

    AnimalRepository animalRepository = new AnimalRepository();


    public List<Animal> findAll() {
        List<Animal> animals = new ArrayList<>();
        animals = animalRepository.findAll();
        return animals;
    }

    public Animal findById(Long id) {
        Animal animal;
        animal = animalRepository.findById(id);
        return animal;
    }

    public Animal save(Animal animal) {
        return animalRepository.save(animal);
    }

    public Animal upate(Animal animal) {
        return animalRepository.update(animal);
    }

    public void delete(Animal existingAnimal) {
        animalRepository.delete(existingAnimal);
    }
}
