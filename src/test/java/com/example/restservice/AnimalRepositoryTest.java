package com.example.restservice;

import com.example.restservice.models.Animal;
import com.example.restservice.repositories.AnimalRepository;
import com.example.restservice.repositories.AnimalRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalRepositoryTest {

    @BeforeEach
    void setUp() {
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