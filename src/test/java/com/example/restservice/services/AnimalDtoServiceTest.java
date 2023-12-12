package com.example.restservice.services;

import com.example.restservice.enums.Kind;
import com.example.restservice.models.Animal;
import com.example.restservice.repositories.AnimalRepository;
import java.util.ArrayList;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import com.example.restservice.dtos.AnimalDTO;
import org.junit.Assert;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Null;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AnimalDtoServiceTest {
    @Mock
    private AnimalRepository animalRepositoryMock;
    @Mock
    private AnimalDTO animalDTO;
    public AnimalDtoServiceTest() {
        this.animalRepositoryMock = mock(AnimalRepository.class);
        this.animalDTO = new AnimalDTO();
    }

    @Test
    void find_all_returns_expected_animals_dto_list_by_size_and_field_list () {
        //Given
        AnimalRepository animalRepositoryMock = mock(AnimalRepository.class);
        //null used as date to allow for various date classes
        animalRepositoryMock.addAnimalToList(new Animal(1L, Kind.CAT, "CAT", 2, 5.0, null));

        AnimalDtoService subject = new AnimalDtoService(animalRepositoryMock, animalDTO);

        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal(1L, Kind.DOG, "Fido", 2, 5.0, null));
        animals.add(new Animal(2L, Kind.CAT, "CAT", 2, 5.0, null));

        Mockito.doReturn(animals).when(animalRepositoryMock).findAll();

        List<AnimalDTO> expected = new ArrayList<>();

        AnimalDTO animalDTO2 = new AnimalDTO();
        animalDTO2.setId(1L);
        animalDTO2.setName("Fido");
        Kind kind2 = Kind.DOG;
        animalDTO2.setKind(kind2);
        animalDTO2.setAge(2);
        animalDTO2.setWeight(5.0);
        animalDTO2.setModifiedDate(null);
        animalDTO2.setFeedType("Dog Food");
        expected.add(animalDTO2);
        
        AnimalDTO animalDTO1 = new AnimalDTO();
        animalDTO1.setId(2L);
        animalDTO1.setName("CAT");
        Kind kind = Kind.CAT;
        animalDTO1.setKind(kind);
        animalDTO1.setAge(2);
        animalDTO1.setWeight(5.0);
        animalDTO1.setModifiedDate(null);
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
    
    @Test
    void find_all_returns_animals_dto_list_by_size_and_feed_type() {
        // Create a list of mock Animal objects
        List<Animal> mockAnimals = new ArrayList<>();
        //null used as date to allow for various date classes
        mockAnimals.add(new Animal(1L, Kind.CAT, Kind.CAT.toString(), 2, 5.0, null));
        mockAnimals.add(new Animal(2L, Kind.DOG, Kind.DOG.toString(), 3, 10.0, null));
        mockAnimals.add(new Animal(3L, Kind.HAMSTER, Kind.HAMSTER.toString(), 1, 0.2, null));

        // Mock the animalRepository to return the mockAnimals list
        when(animalRepositoryMock.findAll()).thenReturn(mockAnimals);

        // Create the AnimalDTOService instance with the mocked repository
        AnimalDtoService subject = new AnimalDtoService(animalRepositoryMock, new AnimalDTO());

        //When
        List<AnimalDTO> animalsDTOList = subject.findAllDTO();

        //Then
        Assert.assertEquals(3, animalsDTOList.size());

        Assert.assertEquals("Cat Food", animalsDTOList.get(0).getFeedType());
        Assert.assertEquals("Dog Food", animalsDTOList.get(1).getFeedType());
        Assert.assertEquals("Sunflower Seeds", animalsDTOList.get(2).getFeedType());
    }
}
