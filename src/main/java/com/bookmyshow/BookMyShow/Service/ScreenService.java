package com.bookmyshow.BookMyShow.Service;

import com.bookmyshow.BookMyShow.DTO.ScreenRequestDTO;
import com.bookmyshow.BookMyShow.Model.Enums.ScreenStatus;
import com.bookmyshow.BookMyShow.Model.Screen;
import com.bookmyshow.BookMyShow.Model.Theatre;
import com.bookmyshow.BookMyShow.Repository.ScreenRepository;
import com.bookmyshow.BookMyShow.Repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreenService {

    @Autowired
    private ScreenRepository screenRepository;

    @Autowired
    private TheatreService theatreService;

    public List<Screen> getAllScreens(){
        return screenRepository.findAll();
    }

    public Screen getScreenByID(int screenId) {
        return screenRepository.findById(screenId).get();
    }

    public Screen createScreen(ScreenRequestDTO screenRequestDTO){

        Screen screen = new Screen();
        screen.setName(screenRequestDTO.getName());
        screen.setStatus(ScreenStatus.AVAILABLE);
        screenRepository.save(screen);

        Theatre theatre = theatreService.getTheatreById(screenRequestDTO.getTheatreId());
        List<Screen> screens = theatre.getScreens();
        screens.add(screen);
        theatre.setScreens(screens);
        theatreService.saveTheatre(theatre);

        return screen;
    }

    public void saveScreen(Screen screen){
        screenRepository.save(screen);
    }

}
