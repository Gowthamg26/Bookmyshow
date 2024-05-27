package com.bookmyshow.BookMyShow.Model;

import com.bookmyshow.BookMyShow.Model.Enums.MovieFeature;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Movie extends BaseModel{

    private String name;

    private String description;

    @ManyToMany
    private List<Actor> actors;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<MovieFeature> features;

}
