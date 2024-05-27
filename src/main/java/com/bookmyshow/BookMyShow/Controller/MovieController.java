package com.bookmyshow.BookMyShow.Controller;

import com.bookmyshow.BookMyShow.DTO.MovieRequestDTO;
import com.bookmyshow.BookMyShow.Exception.InvalidNameException;
import com.bookmyshow.BookMyShow.Model.Movie;
import com.bookmyshow.BookMyShow.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity createMovie(@RequestBody MovieRequestDTO movieRequestDTO){

        if (movieRequestDTO.getDescription().isEmpty() || movieRequestDTO.getDescription().isBlank() || movieRequestDTO.getDescription() == null){
            throw new InvalidNameException("Invalid Movie Description");
        }

        if (movieRequestDTO.getName().isBlank() || movieRequestDTO.getName().isEmpty() || movieRequestDTO.getName() == null){
            throw new InvalidNameException("Invalid Movie Name");
        }

        Movie movie = movieService.createMovie(movieRequestDTO);

        return ResponseEntity.ok(movie);
    }

    @GetMapping("/{name}")
    public ResponseEntity getMovieByName(@PathVariable("name") String movieName){
        return ResponseEntity.ok(movieService.getMovieByName(movieName));
    }

    @GetMapping
    public ResponseEntity getAllMovies(){
        return ResponseEntity.ok(movieService.getAllMovies());
    }
}
