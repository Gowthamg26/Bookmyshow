package com.bookmyshow.BookMyShow.Service;

import com.bookmyshow.BookMyShow.DTO.SeatRequestDTO;
import com.bookmyshow.BookMyShow.Exception.InvalidNameException;
import com.bookmyshow.BookMyShow.Exception.InvalidSeatTypeException;
import com.bookmyshow.BookMyShow.Model.Enums.ScreenStatus;
import com.bookmyshow.BookMyShow.Model.Enums.SeatStatus;
import com.bookmyshow.BookMyShow.Model.Enums.SeatType;
import com.bookmyshow.BookMyShow.Model.Screen;
import com.bookmyshow.BookMyShow.Model.Seat;
import com.bookmyshow.BookMyShow.Model.Theatre;
import com.bookmyshow.BookMyShow.Repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ScreenService screenService;

    public Seat createSeat(SeatRequestDTO seatRequestDTO) {

        Seat seat = new Seat();
        seat.setSeatNumber(seatRequestDTO.getSeatNumber());
        seat.setSeatStatus(SeatStatus.AVAILABLE);
        if (seatRequestDTO.getSeatType().equalsIgnoreCase("GOLD")) {
            seat.setSeatType(SeatType.GOLD);
        } else if (seatRequestDTO.getSeatType().equalsIgnoreCase("SILVER")) {
            seat.setSeatType(SeatType.SILVER);
        } else if (seatRequestDTO.getSeatType().equalsIgnoreCase("PLATINUM")) {
            seat.setSeatType(SeatType.PLATINUM);
        }
        seatRepository.save(seat);

        Screen screen = screenService.getScreenByID(seatRequestDTO.getScreenId());
        List<Seat> seats = screen.getSeats();
        seats.add(seat);
        screen.setSeats(seats);
        screenService.saveScreen(screen);

        return seat;
    }

    public Seat getScreenById(int screenId) {
        return seatRepository.findById(screenId).get();
    }

    public List<Seat> getAllSeats(){
        return seatRepository.findAll();
    }

    public void saveSeat(Seat seat) {
        seatRepository.save(seat);
    }

}
