package com.bookmyshow.BookMyShow.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Theatre extends BaseModel{

    public String name;

    public String address;

    @OneToMany
    public List<Screen> screens;

}
