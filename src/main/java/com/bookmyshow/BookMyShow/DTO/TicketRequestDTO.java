package com.bookmyshow.BookMyShow.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TicketRequestDTO {

    private int showId;

    private List<Integer> showSeatIDs;
}
