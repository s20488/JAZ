package com.westeros.data.repositories;

import com.westeros.data.model.CharacterRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CharacterRepository extends JpaRepository<CharacterRole, Long> {

    //CharacterRole findByActorName(String name);
    @Query("select cr from CharacterRole cr where cr.movie.id = :movieId")
    List<CharacterRole> getActorsFromMovie(@Param("movieId")long id);
}
