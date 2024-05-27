package com.bookmyshow.BookMyShow.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Show extends BaseModel {

    private LocalDateTime showStartTime;

    private LocalDateTime showEndTime;

    @ManyToOne
    private Movie movie;

    @ManyToOne
    private Screen screen;

    @OneToMany
    private List<ShowSeat> showSeats;
}
