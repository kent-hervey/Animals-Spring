package com.example.restservice.repositories;

import com.example.restservice.models.Animal;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class AnimalRepository {

    public static final String DATA_PERSISTENT_ANIMALS_JSON = "data/persistent_animals.json";
    private List<Animal> animals;

    public AnimalRepository() {
        this.animals = new ArrayList<>();

        // Load the fake database of animals
        try (InputStream is = new FileInputStream(DATA_PERSISTENT_ANIMALS_JSON)) {
            ObjectMapper mapper = new ObjectMapper();
            this.animals = mapper.readValue(is, new TypeReference<List<Animal>>() {
            });
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
    public Animal addAnimalToList(Animal animal) {
        String animalsIds = findAll().stream()
                .map(a -> a.getId().toString())
                .reduce("", (a, b) -> a + ", " + b);
        log.info("List of animal Ids:  " + animalsIds);
        Integer maxId = findAll().stream()
                .map(a -> a.getId().intValue())
                .max(Integer::compare).get();
        log.info("Max Id:  " + maxId);
        animal.setId((long) (maxId + 1));
        animals.add(animal);
        return animal;
    }

    public Animal updateAnimalInList(Animal animal) {
        Animal selectedAnimal = animals.get(animal.getId().intValue() - 1);
        selectedAnimal.setName(animal.getName());
        selectedAnimal.setAge(animal.getAge());
        selectedAnimal.setWeight(animal.getWeight());
        return animal;
    }

    public void delete(Animal existingAnimal) {
        animals.remove(existingAnimal);
    }

    public String saveAnimalsToFile() throws Exception {
        String fileInfo = "";
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(findAll());
        log.info("json content to write to file is: " + json);
        log.info("DATA_PERSISTENT_ANIMALS_JSON is: " + DATA_PERSISTENT_ANIMALS_JSON);
        // Write the JSON to the file
        try (OutputStream os = new FileOutputStream(DATA_PERSISTENT_ANIMALS_JSON)) {
            os.write(json.getBytes());
            String thing = os.toString();
            log.info("Animals saved to file.");
            // Get the last save modified date and file size
            File file = new File(DATA_PERSISTENT_ANIMALS_JSON);
            long lastModified = file.lastModified();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date lastModifiedDate = new Date(lastModified);
            String humanReadableLastModifiedDate = sdf.format(lastModifiedDate);

            long fileSize = file.length();
            fileInfo = "File size: " + fileSize + " bytes; last modified: " + humanReadableLastModifiedDate;
            System.out.println(fileInfo);
        } catch (Exception e) {
            log.info("Failed to save animals to JSON file", e);
            throw new Exception("Failed to save animals to JSON file", e);
        }
        return "Animals saved to file via Repository.\n Confirmed as " + fileInfo;
    }
}
