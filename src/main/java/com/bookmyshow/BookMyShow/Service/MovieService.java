package com.bookmyshow.BookMyShow.Service;

import com.bookmyshow.BookMyShow.DTO.MovieRequestDTO;
import com.bookmyshow.BookMyShow.Model.Movie;
import com.bookmyshow.BookMyShow.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Movie createMovie(MovieRequestDTO movieRequestDTO) {

        Movie movie = new Movie();
        movie.setName(movieRequestDTO.getName());
        movie.setDescription(movieRequestDTO.getDescription());
        movieRepository.save(movie);

        return movie;
    }

    public Movie getMovieByName(String movieName) {
        return movieRepository.findMovieByName(movieName);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieByID(int movieID) {
        return movieRepository.findById(movieID).get();
    }

}