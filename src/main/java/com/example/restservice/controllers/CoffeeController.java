package com.example.restservice.controllers;

import com.example.coffeeness.Coffee;
import java.time.LocalDateTime;
import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/api")
@RestController
public class CoffeeController {

    @GetMapping("/coffee")
    public String getCoffee(@RequestParam(required = false) String brand, String name) {
        Coffee coffee = new Coffee();
        coffee.setBrand(brand);
        coffee.setName(name);
        coffee.setDate(LocalDateTime.now());
        return coffee.toString();
    }

    @GetMapping("/coffeex")
    public Coffee getCoffee() {

            Coffee coffee = new Coffee();
            coffee.setBrand("brand");
            coffee.setName("name");
            coffee.setDate(LocalDateTime.now());
            return coffee;

    }


}
