package com.bookmyshow.BookMyShow.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
public class TheatreRequestDTO {

    private String name;

    private String address;

    private int CityId;

    @Override
    public String toString() {
        return "TheatreRequestDTO{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", CityId=" + CityId +
                '}';
    }
}
