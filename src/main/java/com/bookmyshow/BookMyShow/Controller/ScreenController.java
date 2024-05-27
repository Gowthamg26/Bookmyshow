package com.bookmyshow.BookMyShow.Controller;

import com.bookmyshow.BookMyShow.DTO.ScreenRequestDTO;
import com.bookmyshow.BookMyShow.Exception.InvalidIdException;
import com.bookmyshow.BookMyShow.Exception.InvalidNameException;
import com.bookmyshow.BookMyShow.Model.Screen;
import com.bookmyshow.BookMyShow.Service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/screen")
public class ScreenController {

    @Autowired
    ScreenService screenService;

    @GetMapping
    private ResponseEntity getAllScreens(){
        return ResponseEntity.ok(screenService.getAllScreens());
    }

    @GetMapping("/{id}")
    private ResponseEntity getScreenByID(@PathVariable("id") int screenId){
            return ResponseEntity.ok(screenService.getScreenByID(screenId));
    }

    @PostMapping
    private ResponseEntity createScreen(@RequestBody ScreenRequestDTO screenRequestDTO){

        if (screenRequestDTO.getName().isBlank() || screenRequestDTO.getName().isEmpty() || screenRequestDTO.getName() == null){
            throw new InvalidNameException("Invalid Name criteria");
        }

        if (screenRequestDTO.getTheatreId() < 1){
            throw new InvalidIdException("Invalid Theatre ID");
        }
        
        Screen screen = screenService.createScreen(screenRequestDTO);

        return ResponseEntity.ok(screen);
    }
}