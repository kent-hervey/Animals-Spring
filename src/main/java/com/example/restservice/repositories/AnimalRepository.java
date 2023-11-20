package com.example.restservice.repositories;

import com.example.restservice.models.Animal;
import java.util.List;

public interface AnimalRepository {
    String DATA_PERSISTENT_ANIMALS_JSON = "data/persistent_animals.json";

    //find all animals
    List<Animal> findAll();

    Animal findByIdOrElse(Long id);

    Animal findById(Long id);

    Animal addAnimalToList(Animal animal);

    Animal updateAnimalInList(Animal animal);

    void delete(Animal existingAnimal);

    //TODO Is this method doing too much?  Should it be broken up into smaller methods?
    String saveAnimalsToFile() throws Exception;
}
