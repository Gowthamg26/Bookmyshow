package com.bookmyshow.BookMyShow.Service;

import com.bookmyshow.BookMyShow.Model.ShowSeat;
import com.bookmyshow.BookMyShow.Repository.ShowSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowSeatService {

    @Autowired
    private ShowSeatRepository showSeatRepository;

    public void saveShowSeat(ShowSeat showSeat){
        showSeatRepository.save(showSeat);
    }

    public List<ShowSeat> getAllShowSeat(){
        return showSeatRepository.findAll();
    }

    public ShowSeat getShowSeatByID(int showSeatId){
        return showSeatRepository.findById(showSeatId).get();
    }


}