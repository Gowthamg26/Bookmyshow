package com.bookmyshow.BookMyShow.Model;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Actor extends BaseModel {

    private String name;

    private int age;

    @ManyToMany
    private List<Movie> movies;

}