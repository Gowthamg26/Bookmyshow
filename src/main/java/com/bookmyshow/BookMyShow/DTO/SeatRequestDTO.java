package com.bookmyshow.BookMyShow.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatRequestDTO {

    private String seatNumber;

    private int screenId;

    private String seatType;

    @Override
    public String toString() {
        return "SeatRequestDTO{" +
                "seatNumber='" + seatNumber + '\'' +
                ", screenId=" + screenId +
                ", seatType='" + seatType + '\'' +
                '}';
    }
}
