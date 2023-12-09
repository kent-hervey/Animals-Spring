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
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Repository;

//TODO:  Experiment with using interfaces here and elsewhere.  See https://www.baeldung.com/spring-data-repositories and
// https://docs.google.com/document/d/1MOsCW9dj_82jn0RiDYpPgVZN4TjxXE5ATruD85Nuz2M/edit
@Slf4j
@Repository
public class AnimalRepositoryImpl implements AnimalRepository {

    private List<Animal> animals;

    public AnimalRepositoryImpl() {
        this.animals = new ArrayList<>();

        // Load the Animals List from the JSON file
        try (InputStream inputStream = new FileInputStream(DATA_PERSISTENT_ANIMALS_JSON)) {
            ObjectMapper objectMapperFromJson = new ObjectMapper().findAndRegisterModules();
            this.animals = objectMapperFromJson.readValue(inputStream, new TypeReference<List<Animal>>() {
            });
        } catch (Exception e) {
            throw new RuntimeException("Failed to load fake database of animals", e);
        }
    }

    //find all animals
    @Override
    public List<Animal> findAll() {
        return animals;
    }

    @Override
    public Animal findByIdOrElse(Long id) {
        return animals.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Animal findById(Long id) {
        Optional<Animal> optionalAnimail = Optional.of(animals.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(new Animal(-1L, "No Animal", 999, 45.3)));
        return optionalAnimail.get();
    }

    @Override
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

    @Override
    public Animal updateAnimalInList(Animal animal) {
        Animal selectedAnimal = animals.get(animal.getId().intValue() - 1);
        selectedAnimal.setName(animal.getName());
        selectedAnimal.setAge(animal.getAge());
        selectedAnimal.setWeight(animal.getWeight());
        return animal;
    }

    @Override
    public void delete(Animal existingAnimal) {
        animals.remove(existingAnimal);
    }
    //TODO Is this method doing too much?  Should it be broken up into smaller methods?
    @Override
    public String saveAnimalsToFile() throws Exception {
        String fileInfo;
        ObjectMapper objectMapperToJson = new ObjectMapper().findAndRegisterModules();
        String jsonStringAnimals = objectMapperToJson.writeValueAsString(findAll());
        log.info("json content to write to file is: " + jsonStringAnimals);
        log.info("DATA_PERSISTENT_ANIMALS_JSON is: " + DATA_PERSISTENT_ANIMALS_JSON);

        // Write the JSON to the file
        try (OutputStream os = new FileOutputStream(DATA_PERSISTENT_ANIMALS_JSON)) {
            os.write(jsonStringAnimals.getBytes());
            log.info("Animals saved to file.");
            // Get the last save modified date and file size
        } catch (Exception e) {
            log.info("Failed to save animals to JSON file", e);
            throw new Exception("Failed to save animals to JSON file", e);
        }

        File file = new File(DATA_PERSISTENT_ANIMALS_JSON);
        long lastModified = file.lastModified();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String humanReadableLastModifiedDate = sdf.format(lastModified);
        long fileSize = file.length();
        fileInfo = "File size: " + fileSize + " bytes; last modified: " + humanReadableLastModifiedDate;
        log.info("File info of saved json file:  " +  fileInfo);
        return "Animals saved to file via Repository.\n Confirmed as " + fileInfo;
    }
}
