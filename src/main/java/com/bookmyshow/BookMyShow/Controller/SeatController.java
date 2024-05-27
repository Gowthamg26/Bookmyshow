package com.bookmyshow.BookMyShow.Controller;

import com.bookmyshow.BookMyShow.DTO.ScreenRequestDTO;
import com.bookmyshow.BookMyShow.DTO.SeatRequestDTO;
import com.bookmyshow.BookMyShow.Exception.InvalidArgumentException;
import com.bookmyshow.BookMyShow.Exception.InvalidIdException;
import com.bookmyshow.BookMyShow.Exception.InvalidNameException;
import com.bookmyshow.BookMyShow.Model.Seat;
import com.bookmyshow.BookMyShow.Service.ScreenService;
import com.bookmyshow.BookMyShow.Service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.StyledEditorKit;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/seat")
public class SeatController {
    
    @Autowired
    private SeatService seatService;
    
    @PostMapping
    public ResponseEntity createSeats(@RequestBody List<SeatRequestDTO> seatRequestDTOS){

        List<Seat> seats = new ArrayList<>();
        System.out.println(seatRequestDTOS);

        for (SeatRequestDTO seatRequestDTO: seatRequestDTOS){
            if (seatRequestDTO.getSeatNumber().isBlank() || seatRequestDTO.getSeatNumber().isEmpty() || seatRequestDTO.getSeatNumber() == null){
                throw new InvalidNameException("Invalid Name criteria");
            }
            boolean gold = seatRequestDTO.getSeatType().equalsIgnoreCase("GOLD");
            boolean silver = seatRequestDTO.getSeatType().equalsIgnoreCase("SILVER");
            boolean platinum = seatRequestDTO.getSeatType().equalsIgnoreCase("platinum");

            if (( !gold ) && ( !silver ) && (!platinum)) {
                throw new InvalidArgumentException("Invalid Seat Type");
            }
            if (seatRequestDTO.getScreenId() < 1){
                throw new InvalidIdException("Invalid Screen ID");
            }

            seats.add(seatService.createSeat(seatRequestDTO));
        }
        return ResponseEntity.ok(seats);
    }

    @GetMapping
    public ResponseEntity getAllSeats(){
        return ResponseEntity.ok(seatService.getAllSeats());
    }

}
