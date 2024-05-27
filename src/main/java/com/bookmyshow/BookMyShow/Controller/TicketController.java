package com.bookmyshow.BookMyShow.Controller;

import com.bookmyshow.BookMyShow.DTO.TicketRequestDTO;
import com.bookmyshow.BookMyShow.Model.Ticket;
import com.bookmyshow.BookMyShow.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public ResponseEntity bookTicket(@RequestBody TicketRequestDTO ticketRequestDTO){

        Ticket ticket = ticketService.bookTicket(ticketRequestDTO.getShowSeatIDs());
        return ResponseEntity.ok(ticket);
    }

    @GetMapping
    public ResponseEntity getAllTickets(){
        return ResponseEntity.ok(ticketService.getAllTickets());
    }
}
