package com.bookmyshow.BookMyShow.Repository;

import com.bookmyshow.BookMyShow.Model.Actor;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {
}
