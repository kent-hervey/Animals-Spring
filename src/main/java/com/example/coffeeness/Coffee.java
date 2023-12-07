package com.example.coffeeness;


import java.util.Date;
import org.springframework.cglib.core.Local;


// Class
public class Coffee {

    // Getters and setters
    private String name;
    private String brand;
    private Date date;

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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