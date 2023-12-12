package com.example.restservice.repositories;

import com.example.restservice.models.Animal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

import static com.example.restservice.repositories.AnimalRepository.DATA_PERSISTENT_ANIMALS_JSON;

class AnimalRepositoryImplExplorerIT {
    List<Animal> animals = new ArrayList<>();

    @Test
    void confirmExtractedAnimalsFromJsonFileCreatesProperListOfAnimals() {
        //Ensure method should work regardless of Class, Instant, LocalDateTime, or Date
        AnimalRepositoryImpl animalRepositoryImpl = new AnimalRepositoryImpl();

        String JsonPersisentFile = DATA_PERSISTENT_ANIMALS_JSON;
        JsonPersisentFile = "src/test/resources/persistent_animals.json";

        try (InputStream inputStream = new FileInputStream(JsonPersisentFile)) {
            ObjectMapper objectMapperFromJson = new ObjectMapper();
            objectMapperFromJson.findAndRegisterModules();
            animals = objectMapperFromJson.readValue(inputStream, new TypeReference<List<Animal>>() {
            });
        } catch (Exception e) {
            throw new RuntimeException("Failed to load fake database of animals", e);
        }

        animals.stream().forEach(System.out::println);
    }

    //the below mimics the code in the AnimalRepositoryImpl main directory
    //opens the JSON file, reads the JSON file, converts the JSON file to a List<Animal>, and prints the List<Animal>
    //then it updates the List<Animal> with a new modifiedDate
    //then it converts the List<Animal> to a JSON String
    //then it writes the JSON String back to the same JSON file
    @Test
    void confirmExtractedAnimalsSavesToJsonFile() throws JsonProcessingException {
        //Ensure method should work regardless of Class, Instant, LocalDateTime, or Date
        AnimalRepositoryImpl animalRepositoryImpl = new AnimalRepositoryImpl();

        String JsonPersistentFile = "src/test/resources/persistent_animals.json";
        try (InputStream inputStream = new FileInputStream(JsonPersistentFile)) {
            ObjectMapper objectMapperFromJson = new ObjectMapper().findAndRegisterModules();
            //findAndRegisterModules needed when using a date class other than Date
            //objectMapperFromJson.findAndRegisterModules();
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
        objectMapperToJson.findAndRegisterModules();

        //Below converts the List<Animal> to a JSON String
        String animalsAsJsonString;
        animalsAsJsonString = objectMapperToJson.writeValueAsString(animals);

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
    }

    @Test
    void setJsonFileModifiedDateValuesBackToNull() throws JsonProcessingException {
        AnimalRepositoryImpl animalRepositoryImpl = new AnimalRepositoryImpl();
        String JsonPersistentFile = "src/test/resources/persistent_animals.json";
        try (InputStream inputStream = new FileInputStream(JsonPersistentFile)) {
            ObjectMapper objectMapperFromJson = new ObjectMapper();
            objectMapperFromJson.findAndRegisterModules();
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
    void confirmJsonFileMetaDataIsUpdated()  {
        String JsonPersistentFile = "src/test/resources/persistent_animals.json";

        File file = new File(JsonPersistentFile);
        long lastModified = file.lastModified();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String humanReadableLastModifiedDate = sdf.format(lastModified);
        long fileSize = file.length();
        String fileInfo = "File size: " + fileSize + " bytes; last modified: " + humanReadableLastModifiedDate;
        System.out.println("File info of saved json file:  " +  fileInfo);
    }

}