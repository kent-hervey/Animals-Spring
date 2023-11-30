package com.example.restservice.repositories;

import com.example.restservice.models.Animal;
import java.util.List;

public interface AnimalRepository {
    //This constant might be moved back to the AnimalRepositoryImpl
    String DATA_PERSISTENT_ANIMALS_JSON = "data/persistent_animals.json";

    List<Animal> findAll();

    Animal findByIdOrElse(Long id);

    Animal findById(Long id);

    Animal addAnimalToList(Animal animal);

    Animal updateAnimalInList(Animal animal);

    void delete(Animal existingAnimal);

    String saveAnimalsToFile() throws Exception;
}
