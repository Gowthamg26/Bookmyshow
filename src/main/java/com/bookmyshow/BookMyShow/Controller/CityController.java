package com.bookmyshow.BookMyShow.Controller;

import com.bookmyshow.BookMyShow.DTO.CityRequestDTO;
import com.bookmyshow.BookMyShow.Model.City;
import com.bookmyshow.BookMyShow.Service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CityController {

    @Autowired
    public CityService cityService;

    @PostMapping("/city")
    public ResponseEntity SaveCity(@RequestBody CityRequestDTO cityRequestDTO) {

        try {
            String cityName = cityRequestDTO.getName();
            if (cityName.isBlank() || cityName.isEmpty() || cityName == null )
            {
                throw new Exception("Invalid City Name");
            }
            else {
                cityService.saveCity(cityRequestDTO);
                return ResponseEntity.ok("City Saved");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/city/{id}")
    public ResponseEntity getCityByID(@PathVariable("id") int cityID){
        City city = cityService.getCityByID(cityID);
        return ResponseEntity.ok(city);
    }

    @GetMapping("/city")
    public ResponseEntity getAllCities() {
        return ResponseEntity.ok(cityService.getAllCities());
    }

    @DeleteMapping("/city/{id}")
    public ResponseEntity deleteCity(@PathVariable("id") int cityId) {
        Boolean isDeleted = cityService.deleteCity(cityId);
        return ResponseEntity.ok(isDeleted);
    }

}
