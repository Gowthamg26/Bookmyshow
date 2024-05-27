package com.bookmyshow.BookMyShow.Controller;

import com.bookmyshow.BookMyShow.DTO.TheatreRequestDTO;
import com.bookmyshow.BookMyShow.Exception.InvalidNameException;
import com.bookmyshow.BookMyShow.Model.Theatre;
import com.bookmyshow.BookMyShow.Service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class TheatreController {

    @Autowired
    private TheatreService theatreService;

    @GetMapping("/theatre")
    public ResponseEntity getAllTheatres() {
        return ResponseEntity.ok(theatreService.getAllTheatres());
    }

    @PostMapping("/theatre")
    public ResponseEntity createTheatre(@RequestBody TheatreRequestDTO theatreRequestDTO) {

        if (theatreRequestDTO.getName().isEmpty() || theatreRequestDTO.getName().isBlank() || theatreRequestDTO.getName() == null){
            throw new InvalidNameException("Invalid Theatre Name");
        }
        else if (theatreRequestDTO.getAddress().isEmpty() || theatreRequestDTO.getAddress().isBlank() || theatreRequestDTO.getAddress() == null){
            throw new InvalidNameException("Invalid Theatre address");
        }

        System.out.println(theatreRequestDTO);
        theatreService.createTheatre(theatreRequestDTO);
        return ResponseEntity.ok("Theatre Saved");
    }

    @DeleteMapping("/theatre/{id}")
    public ResponseEntity deleteTheatre(@PathVariable("id") int theatreId){
        theatreService.deleteTheatre(theatreId);
        return ResponseEntity.ok("Deleted");
    }

}
