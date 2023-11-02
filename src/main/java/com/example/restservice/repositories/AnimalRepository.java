package com.example.restservice.repositories;

import com.example.restservice.models.Animal;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class AnimalRepository {

    private List<Animal> animals;

    public AnimalRepository() {
        this.animals = new ArrayList<>();

        // Load the fake database of animals
        try (InputStream is = getClass().getResourceAsStream("/fake_animals.json")) {
            ObjectMapper mapper = new ObjectMapper();
            this.animals = mapper.readValue(is, new TypeReference<List<Animal>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Failed to load fake database of animals", e);
        }
    }

    //find all animals
    public List<Animal> findAll() {
        return animals;
    }

    //find animal by id
    public Animal findByIdOrElse(Long id) {
        return animals.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Optional<Animal> findById(Long id) {
        return Optional.of(animals.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(new Animal(0L, "No Animal", 999, 45.3)));
    }

    //add another animal
    public Animal save(Animal animal) {
        animals.add(animal);
        return animal;
    }
}
