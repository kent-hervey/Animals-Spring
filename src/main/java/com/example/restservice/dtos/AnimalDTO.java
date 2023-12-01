package com.example.restservice.dtos;

import com.example.restservice.enums.Kind;
import java.time.LocalDateTime;
import java.util.Objects;
import org.springframework.stereotype.Component;

@Component
public class AnimalDTO {
    private Long id;
    private String name;
    private Kind kind;
    private Integer age;
    private Double weight;
    private LocalDateTime modifiedDate;
    private String feedType;

    public AnimalDTO() {
    }
    //Do we need all three of these constructors?
    public AnimalDTO(Long id, String name, Kind kind, Integer age, Double weight, LocalDateTime modifiedDate, String feedType) {
        this.id = id;
        this.name = name;
        this.kind = kind;
        this.age = age;
        this.weight = weight;
        this.modifiedDate = modifiedDate;
        this.feedType = feedType;
    }
    public AnimalDTO(String name, Kind kind, Integer age, Double weight, LocalDateTime modifiedDate, String feedType) {
        this.name = name;
        this.kind = kind;
        this.age = age;
        this.weight = weight;
        this.modifiedDate = modifiedDate;
        this.feedType = feedType;
    }
    public AnimalDTO(String name, Kind kind, Integer age, Double weight, String feedType) {
        this.name = name;
        this.kind = kind;
        this.age = age;
        this.weight = weight;
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

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
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



