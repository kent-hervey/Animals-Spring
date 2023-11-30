package com.example.restservice.services;

import com.example.restservice.enums.Kind;
import com.example.restservice.models.Animal;
import com.example.restservice.repositories.AnimalRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import com.example.restservice.dtos.AnimalDTO;
import org.junit.Assert;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

public class AnimalDtoServiceTest {
    @Mock
    private AnimalRepository animalRepository;
    @Mock
    private AnimalDTO animalDTO;
    private AnimalService animalService;
    private AnimalDtoService animalDTOService;

    public AnimalDtoServiceTest() {
        this.animalDTO = new AnimalDTO();
    }

    @Test
    void find_all_returns_animals_dto_list () {
        //Given
        AnimalRepository animalRepositoryMock = mock(AnimalRepository.class);
        animalRepositoryMock.addAnimalToList(new Animal(1L, Kind.CAT, "CAT", 2, 5.0, new Date()));

        AnimalDtoService subject = new AnimalDtoService(animalRepositoryMock, animalDTO);
        Date theDate; // Note: Year 123 is equivalent to 2023
        theDate = new Date(123, 0, 1, 12, 0, 0);

        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal(1L, Kind.DOG, "Fido", 2, 5.0, theDate));
        animals.add(new Animal(2L, Kind.CAT, "CAT", 2, 5.0, theDate));


        Mockito.doReturn(animals).when(animalRepositoryMock).findAll();

        List<AnimalDTO> expected = new ArrayList<>();

        AnimalDTO animalDTO2 = new AnimalDTO();
        animalDTO2.setId(1L);
        animalDTO2.setName("Fido");
        Kind kind2 = Kind.DOG;
        animalDTO2.setKind(kind2);
        animalDTO2.setAge(2);
        animalDTO2.setWeight(5.0);
        animalDTO2.setModifiedDate(theDate);
        animalDTO2.setFeedType("Dog Food");
        expected.add(animalDTO2);


        AnimalDTO animalDTO1 = new AnimalDTO();
        animalDTO1.setId(2L);
        animalDTO1.setName("CAT");
        Kind kind = Kind.CAT;
        animalDTO1.setKind(kind);
        animalDTO1.setAge(2);
        animalDTO1.setWeight(5.0);
        animalDTO1.setModifiedDate(theDate);
        animalDTO1.setFeedType("Cat Food");
        expected.add(animalDTO1);



        // When
        List<AnimalDTO> actual = subject.findAllDTO();

        // Then
        System.out.println("expected:  " + expected);
        System.out.println("actual:  " + actual);
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(2, actual.size());
    }


//    @Test
//    void findAll_returns_animals_dto_list() {
//        // Create a list of mock Animal objects
//        List<Animal> mockAnimals = new ArrayList<>();
//        mockAnimals.add(new Animal(1L, "Cat", Kind.CAT.toString(), 2, 5.0, new Date()));
//        mockAnimals.add(new Animal(2L, "Dog", Kind.DOG.toString(), 3, 10.0, new Date()));
//        mockAnimals.add(new Animal(3L, "Hamster", Kind.HAMSTER.toString(), 1, 0.2, new Date()));
//
//        // Mock the animalRepository to return the mockAnimals list
//        when(animalRepository.findAll()).thenReturn(mockAnimals);
//
//        // Create the AnimalDTOService instance with the mocked repository
//        AnimalDtoService animalDTOService = new AnimalDtoService(animalRepository, new AnimalsDTO());
//
//        // Call the findAllDTO method of AnimalDTOService
//        List<AnimalsDTO> animalsDTOList = animalDTOService.findAllDTO();
//
//        // Assert that the returned list contains the expected number of elements
//        Assert.assertEquals(3, animalsDTOList.size());
//
//        // Verify that the feedType property of each AnimalsDTO object is populated correctly
//        for (AnimalsDTO animalDTO : animalsDTOList) {
//            Assert.assertEquals("Cat Food", animalDTO.getFeedType()); // For CAT
//            Assert.assertEquals("Dog Food", animalDTO.getFeedType()); // For DOG
//            Assert.assertEquals("Sunflower Seeds", animalDTO.getFeedType()); // For HAMSTER
//        }
//    }
}
