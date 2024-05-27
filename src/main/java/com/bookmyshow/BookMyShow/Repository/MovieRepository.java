package com.bookmyshow.BookMyShow.Repository;

import com.bookmyshow.BookMyShow.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Movie findMovieByName(String name);
}