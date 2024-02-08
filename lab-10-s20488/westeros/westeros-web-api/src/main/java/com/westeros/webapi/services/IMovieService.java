package com.westeros.webapi.services;

import com.westeros.webapi.contract.ActorCharacterDto;
import com.westeros.webapi.contract.MovieDto;
import com.westeros.webapi.contract.MovieSummaryDto;

import java.util.List;

public interface IMovieService {

    long saveMovie(MovieDto dto);
    long saveActorCharacter(ActorCharacterDto dto);
    long updateMovie(long movieId, MovieDto dto);
    long deleteMovie(long id);
    List<MovieSummaryDto> getAllMovies();
    MovieDto getMovie(long movieId);
}
