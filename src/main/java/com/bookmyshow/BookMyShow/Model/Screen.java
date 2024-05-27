package com.bookmyshow.BookMyShow.Model;

import com.bookmyshow.BookMyShow.Model.Enums.ScreenFeature;
import com.bookmyshow.BookMyShow.Model.Enums.ScreenStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Remove;

import java.util.List;

@Getter
@Setter
@Entity
public class Screen extends BaseModel{

    private String name;

    @OneToMany
    private List<Seat> seats;

    @OneToOne
    private Movie movie;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<ScreenFeature> screenFeatures;

    @Enumerated(EnumType.STRING)
    private ScreenStatus status;

}
