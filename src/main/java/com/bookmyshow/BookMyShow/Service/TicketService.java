package com.bookmyshow.BookMyShow.Service;

import com.bookmyshow.BookMyShow.Exception.PaymentFailedException;
import com.bookmyshow.BookMyShow.Exception.ShowSeatNotAvailableException;
import com.bookmyshow.BookMyShow.Model.Enums.ShowSeatStatus;
import com.bookmyshow.BookMyShow.Model.ShowSeat;
import com.bookmyshow.BookMyShow.Model.Ticket;
import com.bookmyshow.BookMyShow.Model.User;
import com.bookmyshow.BookMyShow.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ShowSeatService showSeatService;

    public List<Ticket> getAllTickets(){
        return ticketRepository.findAll();
    }

    public Ticket bookTicket(List<Integer> showSeatIds){

        double amount = checkAndLockSeat(showSeatIds);
        if (makePayment(amount)){
            checkAndBookSeat(showSeatIds);

            Ticket ticket = new Ticket();
            ticket.setTimeofBooking(LocalDateTime.now());
            ticket.setAmount(amount);

            List<ShowSeat> showSeats = new ArrayList<>();
            for (int showSeatId : showSeatIds){
                ShowSeat showSeat = showSeatService.getShowSeatByID(showSeatId);
                showSeats.add(showSeat);
            }

            ticket.setShowSeats(showSeats);

            ticketRepository.save(ticket);

            return ticket;

        } else {
            checkAndUnlockSeat(showSeatIds);
            throw new PaymentFailedException("Payment Failed");
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public double checkAndLockSeat(List<Integer> showSeatIds){
        for (int showSeatId : showSeatIds){
            ShowSeat showSeat = showSeatService.getShowSeatByID(showSeatId);
            if (!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)){
                throw new ShowSeatNotAvailableException("Seat is already Booked");
            }
        }
        double amount = 0;
        for (int showSeatId : showSeatIds){
            ShowSeat showSeat = showSeatService.getShowSeatByID(showSeatId);
            showSeat.setShowSeatStatus(ShowSeatStatus.LOCKED);
            showSeatService.saveShowSeat(showSeat);
            amount += showSeat.getPrice();
        }
        return amount;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void checkAndUnlockSeat(List<Integer> showSeatIds){
        for (int showSeatId : showSeatIds){
            ShowSeat showSeat = showSeatService.getShowSeatByID(showSeatId);
            showSeat.setShowSeatStatus(ShowSeatStatus.AVAILABLE);
            showSeatService.saveShowSeat(showSeat);
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void checkAndBookSeat(List<Integer> showSeatIds){
        for (int showSeatId : showSeatIds){
            ShowSeat showSeat = showSeatService.getShowSeatByID(showSeatId);
            showSeat.setShowSeatStatus(ShowSeatStatus.BOOKED);
            showSeatService.saveShowSeat(showSeat);
        }
    }

    public boolean makePayment(double amount){
        return true;
    }
}