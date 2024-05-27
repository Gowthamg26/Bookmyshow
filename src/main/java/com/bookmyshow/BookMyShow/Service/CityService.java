package com.bookmyshow.BookMyShow.Service;

import com.bookmyshow.BookMyShow.DTO.CityRequestDTO;
import com.bookmyshow.BookMyShow.Model.City;
import com.bookmyshow.BookMyShow.Repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    public CityRepository cityRepository;

    public City getCity(String cityName) {
        return cityRepository.findCityByName(cityName);
    }

    public City getCityByID(int cityId) {
        return cityRepository.findById(cityId).get();
    }

    public void saveCity(City city) {
        cityRepository.save(city);
    }

    public void saveCity(CityRequestDTO cityRequestDTO) {

        City city = new City();
        city.setName(cityRequestDTO.getName());
        cityRepository.save(city);
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public boolean deleteCity(int id) {
        cityRepository.deleteById(id);
        return true;
    }

}
