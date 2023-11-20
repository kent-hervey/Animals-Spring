package com.example.restservice;

import com.example.restservice.models.Animal;
import com.example.restservice.repositories.AnimalRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalRepositoryImplTest {

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
        AnimalRepositoryImpl animalRepositoryImpl = new AnimalRepositoryImpl();
        Animal animal = animalRepositoryImpl.findById(1L);
        assertEquals(1L, animal.getId());
    }

    @Test
    void save() {
    }
}