package com.example.restservice;

import com.example.restservice.models.Animal;
import com.example.restservice.repositories.AnimalRepository;
import com.example.restservice.repositories.AnimalRepositoryImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.restservice.repositories.AnimalRepository.DATA_PERSISTENT_ANIMALS_JSON;
import static org.junit.jupiter.api.Assertions.*;

class AnimalRepositoryImplExplorer {
    List<Animal> animals = new ArrayList<>();

    @BeforeEach
    void setUp() {
    }

//    @Test
//    void confirmExtractedAnimalsFromJsonFileCreatesProperListOfAnimals() {
//        //Ensure method should work regardless of Class, Instant, LocalDateTime, or Date
//        AnimalRepositoryImpl animalRepositoryImpl = new AnimalRepositoryImpl();
//
//        String JsonPersisentFile = DATA_PERSISTENT_ANIMALS_JSON;
//        JsonPersisentFile = "src/test/resources/persistent_animals.json";
//
//        try (InputStream inputStream = new FileInputStream(JsonPersisentFile)) {
//            ObjectMapper objectMapperFromJson = new ObjectMapper();
//            animals = objectMapperFromJson.readValue(inputStream, new TypeReference<List<Animal>>() {
//            });
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to load fake database of animals", e);
//        }
//
//        animals.stream().forEach(System.out::println);
//    }

    //the below mimics the code in the AnimalRepositoryImpl main directory
    //opens the JSON file, reads the JSON file, converts the JSON file to a List<Animal>, and prints the List<Animal>
    //then it updates the List<Animal> with a new modifiedDate
    //then it converts the List<Animal> to a JSON String
    //then it writes the JSON String back to the same JSON file
    @Test
    void confirmExtractedAnimalsSavesToJsonFile() throws JsonProcessingException, FileNotFoundException {
        //Ensure method should work regardless of Class, Instant, LocalDateTime, or Date
        AnimalRepositoryImpl animalRepositoryImpl = new AnimalRepositoryImpl();

        String JsonPersistentFile = "src/test/resources/persistent_animals.json";
        try (InputStream inputStream = new FileInputStream(JsonPersistentFile)) {
            ObjectMapper objectMapperFromJson = new ObjectMapper();
            this.animals = objectMapperFromJson.readValue(inputStream, new TypeReference<List<Animal>>() {
            });
        } catch (Exception e) {
            throw new RuntimeException("Failed to load fake database of animals", e);
        }

        this.animals.stream().forEach(System.out::println);

        //so now animals is all loaded, lets update id=1 with new modifiedDate
        animals.stream()
                .filter(a -> a.getId().equals(1L))
                .findFirst()
                .orElse(null).setModifiedDate();

        //// Now we need to save the animals to the JSON file

        // a new instance of the Jackson ObjectMapper class.  This will be used to convert the List<Animal> to a JSON String
        ObjectMapper objectMapperToJson = new ObjectMapper();

        String debug2 = "two";

        //Below converts the List<Animal> to a JSON String
        String animalsAsJsonString = objectMapperToJson.writeValueAsString(animals);

        //create an output stream to write the JSON String to the JSON file
        //Create a new instance of FileOutputStream with the JsonPersitentFile path as an argument.
        // //This opens the file in write mode, meaning any existing data will be overwritten.
        try (OutputStream outputStream = new FileOutputStream(JsonPersistentFile)) {
            //animalsAsJsonString.getBytes() converts the animalsAsJsonString variable, which is assumed to be a string, to a byte array.
            //outputStream.write(animalsAsJsonString.getBytes()) writes the byte array to the open file.
            outputStream.write(animalsAsJsonString.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("Failed to save fake database of animals", e);
        }
        String debug3 = "three";
    }

    @Test
    void setJsonFileModifiedDateValuesBackToNull() throws JsonProcessingException {
        AnimalRepositoryImpl animalRepositoryImpl = new AnimalRepositoryImpl();
        String JsonPersistentFile = "src/test/resources/persistent_animals.json";
        try (InputStream inputStream = new FileInputStream(JsonPersistentFile)) {
            ObjectMapper objectMapperFromJson = new ObjectMapper();
            this.animals = objectMapperFromJson.readValue(inputStream, new TypeReference<List<Animal>>() {
            });
        } catch (Exception e) {
            throw new RuntimeException("Failed to load fake database of animals", e);
        }
        this.animals.stream().forEach(System.out::println);
        System.out.println("--------------------------------------------------");
        animals.stream()
                .forEach(a -> a.setModifiedDateToNull());
        this.animals.stream().forEach(System.out::println);
        ObjectMapper objectMapperToJson = new ObjectMapper();
        String debug2 = "two";
        String animalsAsJsonString = objectMapperToJson.writeValueAsString(animals);
        try (OutputStream outputStream = new FileOutputStream(JsonPersistentFile)) {
            outputStream.write(animalsAsJsonString.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("Failed to save fake database of animals", e);
        }
    }


    @Test
    void findAll() {
    }




    @Test
    void findByIdOrElse() {
    }

    @Test
    void findById() {
        AnimalRepository animalRepository = new AnimalRepositoryImpl();
        Animal animal = animalRepository.findById(1L);
        assertEquals(1L, animal.getId());
    }

    @Test
    void save() {
    }
}