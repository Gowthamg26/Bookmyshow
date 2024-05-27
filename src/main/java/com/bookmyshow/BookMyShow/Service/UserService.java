package com.bookmyshow.BookMyShow.Service;

import com.bookmyshow.BookMyShow.DTO.BookTicketDTO;
import com.bookmyshow.BookMyShow.Exception.UserLoginException;
import com.bookmyshow.BookMyShow.Exception.UserSignupException;
import com.bookmyshow.BookMyShow.Model.Show;
import com.bookmyshow.BookMyShow.Model.Ticket;
import com.bookmyshow.BookMyShow.Model.User;
import com.bookmyshow.BookMyShow.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShowService showService;

    @Autowired
    private TicketService ticketService;

    public Boolean login(String email, String password){
        User savedUser = userRepository.findUserByEmail(email);
        if(savedUser == null){
            throw new UserLoginException("User Doesn't Exists");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(password, savedUser.getPassword())) {
            return true;
        }
        throw new UserLoginException("Invalid Email & Password");
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public User signup (String email, String password){
        User savedUser = userRepository.findUserByEmail(email);
        if( savedUser != null){
            throw new UserSignupException("User already Exists");
        }

        savedUser = new User();
        savedUser.setEmail(email);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        savedUser.setPassword(encoder.encode(password));
        savedUser.setTickets(new ArrayList<>());
        return userRepository.save(savedUser);

    }
    
    public Ticket bookTicket(BookTicketDTO bookTicketDTO){

        Ticket ticket = ticketService.bookTicket(bookTicketDTO.getSeatIDs());

        User user = userRepository.findUserByEmail(bookTicketDTO.getEmailID());

        Show show = showService.getShowById(bookTicketDTO.getShowID());

        ticket.setShow(show);

        List<Ticket> tickets = user.getTickets();
        tickets.add(ticket);
        user.setTickets(tickets);

        userRepository.save(user);
        
        return ticket;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
