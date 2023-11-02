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

    //print a string
public String printString(String string) {
        return string;
    }


    public Animal findById(Long id) {

        System.out.println("Id coming into service class: " + id);

        //id = 1L;

        System.out.println("Id after being set to 1L: " + id);
        Optional<Animal> animal;
        animal = animalRepository.findById(id);

        //I need a log output here using logger
        System.out.println("Animal from service class: " + animal);


        return animal.orElse(new Animal(0L, "No Animal", 999, 45.3));
    }
}
