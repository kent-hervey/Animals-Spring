package com.example.restservice.dtos;

import com.example.restservice.enums.Kind;
import java.util.Date;
import java.util.Objects;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public class AnimalDTO {
    private Long id;
    private String name;
    private Kind kind;
    private Integer age;
    private Double weight;
    private Date modifiedDate;
    private String feedType;

    // Constructors, getters, setters

    public AnimalDTO() {
    }

    public AnimalDTO(Long id, String name, Kind kind, Integer age, Double weight, Date modifiedDate, String feedType) {
        this.id = id;
        this.name = name;
        this.kind = kind;
        this.age = age;
        this.weight = weight;
        this.modifiedDate = modifiedDate;
        this.feedType = feedType;
    }



    public Long getId() {
        return id;
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

    public Kind getKind() {
        return kind;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
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

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getFeedType() {
        return feedType;
    }

    public void setFeedType(String feedType) {
        this.feedType = feedType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AnimalDTO that = (AnimalDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(kind, that.kind) && Objects.equals(age, that.age) && Objects.equals(weight, that.weight) && Objects.equals(modifiedDate, that.modifiedDate) && Objects.equals(feedType, that.feedType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, kind, age, weight, modifiedDate, feedType);
    }

    @Override
    public String toString() {
        return "AnimalsDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", kind='" + kind + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", modifiedDate=" + modifiedDate +
                ", feedType='" + feedType + '\'' +
                '}';
    }
}



