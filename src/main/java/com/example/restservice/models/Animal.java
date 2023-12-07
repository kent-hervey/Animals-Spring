package com.example.restservice.models;

import com.example.restservice.enums.Kind;
import java.util.Date;
import java.util.Objects;

public class Animal {
    private Long id;
    private Kind kind;
    private String name;
    private Integer age;
    private Double weight;
    private Date modifiedDate;

    public Animal() {
    }

    public Animal(Long id, String name, Integer age, Double weight) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    public Animal(Long id, Kind kind, String name, Integer age, Double weight, Date modifiedDate) {
        this.id = id;
        this.kind = kind;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.modifiedDate = modifiedDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getWeight() {
        return weight;
    }

    public void setModifiedDate() {
        this.modifiedDate = Date.from(java.time.Instant.now());
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }


    public Kind getKind() {
        return kind;
    }

    public Long getId() {
        return id;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Animal animal = (Animal) o;
        return Objects.equals(id, animal.id) && Objects.equals(kind, animal.kind) && Objects.equals(name, animal.name) && Objects.equals(age, animal.age) && Objects.equals(weight, animal.weight) && Objects.equals(modifiedDate, animal.modifiedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, kind, name, age, weight, modifiedDate);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", kind='" + kind + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", modifiedDate=" + modifiedDate +
                '}';
    }
    //This method for testing purposes only
    public void setModifiedDateToNull() {
        this.modifiedDate = null;
    }
}
