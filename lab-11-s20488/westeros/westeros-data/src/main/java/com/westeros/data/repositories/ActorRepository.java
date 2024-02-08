package com.westeros.data.repositories;

import com.westeros.data.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Long> {

    @Query("select actor from Actor actor where actor.sourceId= :id")
    List<Actor> findAllBySourceId(@Param("id") int sourceId);
}
