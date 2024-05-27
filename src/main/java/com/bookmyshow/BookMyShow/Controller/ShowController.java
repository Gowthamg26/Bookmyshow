package com.bookmyshow.BookMyShow.Controller;

import com.bookmyshow.BookMyShow.DTO.ShowRequestDTO;
import com.bookmyshow.BookMyShow.Model.Show;
import com.bookmyshow.BookMyShow.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    private ShowService showService;

    @PostMapping
    public ResponseEntity createShow(@RequestBody ShowRequestDTO showRequestDTO){
        Show show = showService.createShow(showRequestDTO);
        return ResponseEntity.ok(show);
    }

    @GetMapping
    public ResponseEntity getAllShows(){
        return ResponseEntity.ok(showService.getAllShows());
    }
}