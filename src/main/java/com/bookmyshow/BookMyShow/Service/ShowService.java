package com.bookmyshow.BookMyShow.Service;

import com.bookmyshow.BookMyShow.DTO.ShowRequestDTO;
import com.bookmyshow.BookMyShow.Model.Enums.ShowSeatStatus;
import com.bookmyshow.BookMyShow.Model.Screen;
import com.bookmyshow.BookMyShow.Model.Seat;
import com.bookmyshow.BookMyShow.Model.Show;
import com.bookmyshow.BookMyShow.Model.ShowSeat;
import com.bookmyshow.BookMyShow.Repository.ShowRepository;
import com.bookmyshow.BookMyShow.Repository.ShowSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    public ShowRepository showRepository;

    @Autowired
    public ScreenService screenService;

    @Autowired
    public ShowSeatService showSeatService;

    @Autowired
    public MovieService movieService;

    public Show createShow(ShowRequestDTO showRequestDTO){

        Show show = new Show();
        show.setShowStartTime(LocalDateTime.now().plusDays(1));
        show.setShowEndTime(show.getShowStartTime().plusHours(3));
        show.setMovie(movieService.getMovieByID(showRequestDTO.getMovieID()));

        Screen screen = screenService.getScreenByID(showRequestDTO.getScreenID());
        show.setScreen(screen);
        List<Seat> seats = screen.getSeats();
        List<ShowSeat> showSeats = new ArrayList<>();

        for(Seat seat: seats){

           ShowSeat showSeat = new ShowSeat();
           showSeat.setShowSeatStatus(ShowSeatStatus.AVAILABLE);
           showSeat.setPrice(200);
           showSeat.setSeat(seat);

           showSeats.add(showSeat);
           showSeatService.saveShowSeat(showSeat);
        }
        show.setShowSeats(showSeats);
        showRepository.save(show);
        return show;
    }

    public List<Show> getAllShows(){
        return showRepository.findAll();
    }

    public Show getShowById(int showId){
        return showRepository.findById(showId).get();
    }

}