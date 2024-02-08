package com.westeros.data.repositories;

import com.westeros.data.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MoviesRepository extends JpaRepository<Movie, Long> {

    @Query("select movie from Movie movie join fetch movie.genres where movie.id=:id")
    Movie findAllWithRoles(@Param("id")long id);
}
