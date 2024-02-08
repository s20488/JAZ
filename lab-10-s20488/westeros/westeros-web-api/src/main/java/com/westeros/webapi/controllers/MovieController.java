package com.westeros.webapi.controllers;

import com.westeros.webapi.contract.ActorCharacterDto;
import com.westeros.webapi.contract.MovieDto;
import com.westeros.webapi.contract.MovieSummaryDto;
import com.westeros.webapi.services.IMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
public class MovieController {

    private final IMovieService movieService;

    @PostMapping
    public ResponseEntity saveMovie(@RequestBody MovieDto movie){
        var id = movieService.saveMovie(movie);
        return ResponseEntity.ok(id);
    }

    @PostMapping("/actor")
    public ResponseEntity saveActorCharacter(@RequestBody ActorCharacterDto actorCharacter){
        var id = movieService.saveActorCharacter(actorCharacter);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateMovie(@PathVariable("id")long id, @RequestBody MovieDto movie){
        var updateId = movieService.updateMovie(id, movie);
        return ResponseEntity.ok(updateId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMovie(@PathVariable("id")long id){
        var deleteId = movieService.deleteMovie(id);
        if(deleteId != 0) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<MovieSummaryDto>> getAllMovies(){
        var movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovie(@PathVariable("id") long id){
        var movie = movieService.getMovie(id);
        if(movie != null) {
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
