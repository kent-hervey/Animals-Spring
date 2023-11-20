package com.example.restservice.controllers;

import com.example.restservice.services.AnimalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api")
@RestController

/**
 * The type File controller.
 * This is a controller for the File entity.
 * It will have methods for:
 * 1.  Saving to file the current state of the database aka the animals ArrayList
 * 2.  See one file
 */
public class FileController {

    private final AnimalService animalService;

    public FileController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping("save")
    public ResponseEntity<String> saveAnimals() throws Exception {
        try {
            String returnFromService = animalService.persistChanges();
            log.info("Data coming back from service:  " + returnFromService);

            // Return a success response
            return ResponseEntity.ok("All changes persisted, " + returnFromService);
        } catch (Exception e) {
            // Handle any exceptions and return an appropriate response, e.g., 500 Internal Server Error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save animals to file.");
        }
    }

//TODO remove all the below comments

//Notes on how all this could work:

/*   Create a method in the AnimalRepository class to save the animals to the JSON file. This method can take a list of animals as input and write it to the JSON file using the ObjectMapper class.
        Call the saveAnimalsToFile() method whenever you want to save changes to the JSON file. For example, you could call it after saving a new animal or updating an existing animal.

In RESTful design, the choice of HTTP verb should align with the semantics of the operation you're performing. When it comes to saving data to a file, it's a good practice to use the `POST` HTTP verb, even if the operation might not change anything. This is because `POST` is often used for actions that may have side effects and don't have a direct one-to-one mapping to CRUD (Create, Read, Update, Delete) operations.

In your case, the `saveAnimalsToFile()` method doesn't directly map to a traditional CRUD operation (Create, Read, Update, Delete) on a resource. It's more of an action that takes place, and it may or may not result in changes. Using `POST` for this operation is a common practice to indicate that you are triggering a specific action.

Here's how you can set up your endpoint:



@PostMapping("/save")
public void saveAnimals() throws Exception {
    animalService.saveAnimals();
}

```java
@PostMapping("/save")
public ResponseEntity<String> saveAnimalsToFile() {
    try {
        // Call your saveAnimalsToFile() method here

        // Return a success response
        return ResponseEntity.ok("All changes persisted");
    } catch (Exception e) {
        // Handle any exceptions and return an appropriate response, e.g., 500 Internal Server Error
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save animals to file.");
    }
}
```

Using the `POST` method for this operation provides a clear indication that you're performing an action, and it allows for potential side effects or changes to occur. If the operation is idempotent, meaning it has the same result whether you call it once or multiple times, it conforms to the principles of RESTful design. However, you should ensure that your `saveAnimalsToFile()` method does not have any unintended side effects if called multiple times with the same data.
===or from Bard

To save changes to the JSON file, you can use the following steps:

Create a method in the AnimalRepository class to save the animals to the JSON file. This method can take a list of animals as input and write it to the JSON file using the ObjectMapper class.
Call the saveAnimalsToFile() method whenever you want to save changes to the JSON file. For example, you could call it after saving a new animal or updating an existing animal.
Here is an example of a saveAnimalsToFile() method:

Java
private void saveAnimalsToFile(List<Animal> animals) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(animals);

    // Write the JSON to the file
    try (OutputStream os = new FileOutputStream("/persistent_animals.json")) {
        os.write(json.getBytes());
    } catch (Exception e) {
        throw new Exception("Failed to save animals to JSON file", e);
    }
}


*/


}