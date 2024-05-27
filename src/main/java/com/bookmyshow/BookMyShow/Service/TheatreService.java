package com.bookmyshow.BookMyShow.Service;

import com.bookmyshow.BookMyShow.DTO.TheatreRequestDTO;
import com.bookmyshow.BookMyShow.Model.City;
import com.bookmyshow.BookMyShow.Model.Theatre;
import com.bookmyshow.BookMyShow.Repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheatreService{

    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private CityService cityService;

    public List<Theatre> getAllTheatres() {
        return theatreRepository.findAll();
    }

    public void createTheatre(TheatreRequestDTO theatreRequestDTO) {
        Theatre theatre = new Theatre();
        theatre.setName(theatreRequestDTO.getName());
        theatre.setAddress(theatreRequestDTO.getAddress());
        theatreRepository.save(theatre);

        City city = cityService.getCityByID(theatreRequestDTO.getCityId());
        List<Theatre> theatres = city.getTheatres();
        theatres.add(theatre);
        city.setTheatres(theatres);
        cityService.saveCity(city);
    }

    public void saveTheatre(Theatre theatre) {
        theatreRepository.save(theatre);
    }

    public Theatre getTheatreById(int theatreId) {
        return theatreRepository.findById(theatreId).get();
    }

    public void deleteTheatre(int theatreId) {
        theatreRepository.deleteById(theatreId);
    }

}
