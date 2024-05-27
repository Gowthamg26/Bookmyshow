package com.bookmyshow.BookMyShow.Controller;

import com.bookmyshow.BookMyShow.DTO.BookTicketDTO;
import com.bookmyshow.BookMyShow.DTO.UserRequestDTO;
import com.bookmyshow.BookMyShow.Exception.InvalidArgumentException;
import com.bookmyshow.BookMyShow.Exception.UserLoginException;
import com.bookmyshow.BookMyShow.Model.Ticket;
import com.bookmyshow.BookMyShow.Model.User;
import com.bookmyshow.BookMyShow.Service.TicketService;
import com.bookmyshow.BookMyShow.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity userLogin(@RequestBody UserRequestDTO userRequestDTO){

        if (userRequestDTO.getEmail().isEmpty() || userRequestDTO.getEmail().isBlank() || userRequestDTO.getEmail() == null){
            throw new InvalidArgumentException("Invalid email Id");
        }

        if (userRequestDTO.getPassword().isEmpty() || userRequestDTO.getPassword().isBlank() || userRequestDTO.getPassword() == null){
            throw new InvalidArgumentException("Invalid Password");
        }

        if (userService.login(userRequestDTO.getEmail(), userRequestDTO.getPassword())){
            return ResponseEntity.ok("Login Succeeded");
        } else {
            throw new UserLoginException("Login failed");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity userSignup(@RequestBody UserRequestDTO userRequestDTO){
        return ResponseEntity.ok(userService.signup(userRequestDTO.getEmail(), userRequestDTO.getPassword()));
    }

    @PostMapping("/bookTicket")
    public ResponseEntity bookTicket(@RequestBody BookTicketDTO bookTicketDTO){

        System.out.println(bookTicketDTO.getSeatIDs());

        Ticket ticket = userService.bookTicket(bookTicketDTO);
        return ResponseEntity.ok(ticket);

    }

    @GetMapping
    public ResponseEntity GetAllUser(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

}