package com.westeros.webapi.services;

import com.westeros.data.model.Actor;
import com.westeros.data.model.CharacterRole;
import com.westeros.data.model.Movie;
import com.westeros.data.repositories.ICatalogData;
import com.westeros.webapi.contract.ActorCharacterDto;
import com.westeros.webapi.contract.MovieDto;
import com.westeros.webapi.contract.MovieSummaryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService implements IMovieService{
    private final ICatalogData db;
    @Override
    public long saveMovie(MovieDto dto) {

        var movieEntity = new Movie();
        movieEntity.setRuntime(dto.getRuntime());
        movieEntity.setOverview(dto.getOverview());
        movieEntity.setReleaseDate(dto.getReleaseDate());
        movieEntity.setBudget(dto.getBudget());
        movieEntity.setOriginalTitle(dto.getTitle());
        movieEntity.setHomepage(dto.getHomepage());
        movieEntity.setOriginalLanguage(dto.getLanguage());
        db.getMovies().save(movieEntity);
        return movieEntity.getId();
    }

    @Override
    public long saveActorCharacter(ActorCharacterDto dto) {

        var actorCharacterEntity = new Actor();
        actorCharacterEntity.setName(dto.getActorName());

        var characterRole = new CharacterRole();
        characterRole.setName(dto.getCharacterName());
        characterRole.setActor(actorCharacterEntity);

        actorCharacterEntity.getCharacters().add(characterRole);

        db.getActors().save(actorCharacterEntity);
        return actorCharacterEntity.getId();
    }

    @Override
    public long updateMovie(long movieId, MovieDto dto) {
        var movieEntityOptional = db.getMovies().findById(movieId);
        if (movieEntityOptional.isPresent()) {
            Movie movieEntity = movieEntityOptional.get();
            movieEntity.setRuntime(dto.getRuntime());
            movieEntity.setOverview(dto.getOverview());
            movieEntity.setReleaseDate(dto.getReleaseDate());
            movieEntity.setBudget(dto.getBudget());
            movieEntity.setOriginalTitle(dto.getTitle());
            movieEntity.setHomepage(dto.getHomepage());
            movieEntity.setOriginalLanguage(dto.getLanguage());
            db.getMovies().save(movieEntity);
            return movieEntity.getId();
        }
        return 0;
    }

    @Override
    public long deleteMovie(long id) {
        var movieEntityOptional = db.getMovies().findById(id);
        if (movieEntityOptional.isPresent()) {
            db.getMovies().delete(movieEntityOptional.get());
            return 1;
        }
        return 0;
    }

    @Override
    public List<MovieSummaryDto> getAllMovies() {
        List<Movie> movieEntities = db.getMovies().findAll();
        List<MovieSummaryDto> movieSummaries = new ArrayList<>();
        for (Movie movieEntity : movieEntities) {
            var dto = new MovieSummaryDto();
            dto.setId(movieEntity.getId());
            dto.setTitle(movieEntity.getOriginalTitle());
            dto.setHomepage(movieEntity.getHomepage());
            dto.setLanguage(movieEntity.getOriginalLanguage());
            movieSummaries.add(dto);
        }
        return movieSummaries;
    }

    @Override
    public MovieDto getMovie(long movieId) {
        var movieEntityOptional = db.getMovies().findById(movieId);
        if (movieEntityOptional.isPresent()) {
            Movie movieEntity = movieEntityOptional.get();
            var dto = new MovieDto();
            dto.setRuntime(movieEntity.getRuntime());
            dto.setOverview(movieEntity.getOverview());
            dto.setReleaseDate(movieEntity.getReleaseDate());
            dto.setBudget(movieEntity.getBudget());
            dto.setTitle(movieEntity.getOriginalTitle());
            dto.setHomepage(movieEntity.getHomepage());
            dto.setLanguage(movieEntity.getOriginalLanguage());
            return dto;
        }
        return null;
    }
}
