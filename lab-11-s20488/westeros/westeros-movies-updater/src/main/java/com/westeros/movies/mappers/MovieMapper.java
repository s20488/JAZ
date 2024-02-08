package com.westeros.movies.mappers;

import com.westeros.data.model.Movie;
import com.westeros.moviesclient.contract.MovieDto;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper implements IMapEntities<MovieDto, Movie>{
    @Override
    public Movie map(MovieDto movieDto) {
        return map(movieDto, new Movie());
    }

    @Override
    public Movie map(MovieDto movieDto, Movie movie) {
        movie.setAdult(movieDto.isAdult());
        movie.setBudget(movieDto.getBudget());
        movie.setBackdropPath(movieDto.getBackdropPath());
        movie.setHomepage(movieDto.getHomepage());
        movie.setOriginalLanguage(movieDto.getOriginalLanguage());
        movie.setOriginalTitle(movieDto.getOriginalTitle());
        movie.setOverview(movieDto.getOverview());
        movie.setPopularity(movieDto.getPopularity());
        movie.setPosterPath(movieDto.getPosterPath());
        movie.setReleaseDate(movieDto.getReleaseDate());
        movie.setRuntime(movieDto.getRuntime());
        movie.setVoteCount(movieDto.getVoteCount());
        movie.setVoteAverage(movieDto.getVoteAverage());
        movie.setSourceId(movieDto.getId());
        return movie;
    }
}
