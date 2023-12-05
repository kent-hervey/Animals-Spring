package com.example.coffeeness;

import java.time.ZonedDateTime;
import org.springframework.cglib.core.Local;


// Class
public class Coffee {

    // Getters and setters
    private String name;
    private String brand;
    private ZonedDateTime date;

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Coffee{" +
                "name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", date=" + date +
                '}';
    }
}