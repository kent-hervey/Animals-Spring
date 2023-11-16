package com.example.restservice.services;

import com.example.restservice.dtos.AnimalDTO;
import com.example.restservice.enums.Kind;
import com.example.restservice.models.Animal;
import com.example.restservice.repositories.AnimalRepository;
import java.nio.channels.NonWritableChannelException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service

public class AnimalDtoService {

    AnimalRepository animalRepository = new AnimalRepository();
    AnimalDTO animalDTO = new AnimalDTO();

    private Map<Kind, String> kindToFeedTypeMap = new HashMap<>();

    public AnimalDtoService(AnimalRepository animalRepository, AnimalDTO animalDTO) {
        this.animalRepository = animalRepository;
        this.animalDTO = animalDTO;
        initializeKindToFeedTypeMap();
    }

    public List<AnimalDTO> findAllDTO() {
        if (kindToFeedTypeMap.isEmpty()) {
            initializeKindToFeedTypeMap();
        }
        List<AnimalDTO> animalsDTO = new ArrayList<>();
        List<Animal> animals = new ArrayList<>();
        animals = animalRepository.findAll();
        for(Animal animal : animals) {
            AnimalDTO collecting = new AnimalDTO();
            collecting.setId(animal.getId());
            collecting.setName(animal.getName());
            collecting.setKind(animal.getKind());
            collecting.setAge(animal.getAge());
            collecting.setWeight(animal.getWeight());
            collecting.setModifiedDate(animal.getModifiedDate());
            Kind theKind = animal.getKind();
            String feedType = kindToFeedTypeMap.get(theKind);
            collecting.setFeedType(feedType);
            animalsDTO.add(collecting);
        }
        log.info("Service class found all animals in " + animalsDTO);
        return animalsDTO;
    }

    public AnimalDTO findById(Long id) {
        AnimalDTO animalDTO = new AnimalDTO();
        Animal animal = animalRepository.findById(id);
        animalDTO.setId(animal.getId());
        animalDTO.setName(animal.getName());
        animalDTO.setKind(animal.getKind());
        animalDTO.setAge(animal.getAge());
        animalDTO.setWeight(animal.getWeight());
        animalDTO.setModifiedDate(animal.getModifiedDate());
        Kind theKind = animal.getKind();
        String feedType = kindToFeedTypeMap.get(theKind);
        animalDTO.setFeedType(feedType);
        return animalDTO;
    }

    private void initializeKindToFeedTypeMap() {
        kindToFeedTypeMap.put(Kind.CAT, "Cat Food");
        kindToFeedTypeMap.put(Kind.DOG, "Dog Food");
        kindToFeedTypeMap.put(Kind.HAMSTER, "Sunflower Seeds");
        kindToFeedTypeMap.put(Kind.GERBIL, "Hamster Mix");
        kindToFeedTypeMap.put(Kind.FISH, "Fish Food");
        kindToFeedTypeMap.put(Kind.STEER, "Sweet Feed");
        kindToFeedTypeMap.put(Kind.GOAT, "Sweet Feed");
        kindToFeedTypeMap.put(Kind.SHEEP, "Sweet Feed");
        kindToFeedTypeMap.put(Kind.HORSE, "Hay and Grain");
        kindToFeedTypeMap.put(Kind.DONKEY, "Hay and Grain");
        kindToFeedTypeMap.put(Kind.REPTILE, "Live Insects or Pellets");
        kindToFeedTypeMap.put(Kind.BIRD, "Birdseed");
    }
}
