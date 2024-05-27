package com.bookmyshow.BookMyShow.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookTicketDTO {

    private String emailID;

    private int showID;

    private List<Integer> seatIDs;

}
