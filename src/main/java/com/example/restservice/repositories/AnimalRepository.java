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
        try (InputStream is = getClass().getResourceAsStream("/persistent_animals.json")) {
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

    public Animal findById(Long id) {
        Optional<Animal> optionalAnimail = Optional.of(animals.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(new Animal(-1L, "No Animal", 999, 45.3)));
        return optionalAnimail.get();
    }

    //add another animal
    public Animal save(Animal animal) {
        animal.setId((long) (animals.size() + 1));
        animals.add(animal);
        return animal;

    }

        public Animal update(Animal animal) {
            Animal selectedAnimal = animals.get(animal.getId().intValue() - 1);
            selectedAnimal.setName(animal.getName());
            selectedAnimal.setAge(animal.getAge());
            selectedAnimal.setWeight(animal.getWeight());
            return animal;
        }

    public void delete(Animal existingAnimal) {
        animals.remove(existingAnimal);
    }
}
