package com.westeros.movies.updater;

import com.westeros.data.model.*;
import com.westeros.data.repositories.ICatalogData;
import com.westeros.moviesclient.*;
import com.westeros.moviesclient.contract.*;
import com.westeros.tools.safeinvoker.SafeInvoking;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MoviesUpdater implements IUpdateMovies{

    private final ICatalogData data;
    private final IMoviesClient moviesClient;
    private final IMoviesDictionariesClient dictionariesClient;
    private final SafeInvoking invoker;

    public MoviesUpdater(ICatalogData dbCatalog,
                         IMoviesClient moviesClient,
                         IMoviesDictionariesClient dictionariesClient,
                         SafeInvoking invoker) {
        this.data = dbCatalog;
        this.moviesClient = moviesClient;
        this.dictionariesClient = dictionariesClient;
        this.invoker = invoker;
    }

    @Override
    public void updateByDateRange(LocalDate from, LocalDate to) {
        var languagesDto = dictionariesClient.getLanguages();
        var genresDto = dictionariesClient.getGenres();

        var languages = languagesDto.stream().map(dto -> {
            Language language = new Language();
            language.setEnglishName(dto.getEnglishName());
            language.setName(dto.getName());
            return language;
        }).toList();

        var genres = genresDto.stream().map(dto -> {
            Genre genre = new Genre();
            genre.setName(dto.getName());
            return genre;
        }).toList();

        var result = moviesClient.getByDateRange(from, to);
        List<Integer> sourceIds = new ArrayList<>();
        for(var page =1; page<= result.getTotalPages(); page++){
            sourceIds.addAll(moviesClient.getByDateRange(from, to, page)
                    .getResults()
                    .stream()
                    .mapToLong(MovieSummaryDto::getId)
                    .mapToInt(Math::toIntExact)
                    .boxed()
                    .toList());
        }
        var sourceIdsToIgnore = data.getMovies().getSourceIdsIn(sourceIds);
        sourceIdsToIgnore.forEach(sourceIds::remove);

        var moviesDto = sourceIds.stream().map(moviesClient::getMovie).toList();

        List<Movie> movies = moviesDto.stream().map(dto -> {
            Movie movie = new Movie();
            movie.setAdult(dto.isAdult());
            movie.setBudget(dto.getBudget());
            movie.setBackdropPath(dto.getBackdropPath());
            movie.setHomepage(dto.getHomepage());
            movie.setOriginalLanguage(dto.getOriginalLanguage());
            movie.setOriginalTitle(dto.getOriginalTitle());
            if(dto.getOverview().length()>200)
                dto.setOverview(dto.getOverview().substring(0,199));
            movie.setOverview(dto.getOverview());
            movie.setPopularity(dto.getPopularity());
            movie.setPosterPath(dto.getPosterPath());
            movie.setReleaseDate(dto.getReleaseDate());
            movie.setRuntime(dto.getRuntime());
            movie.setVoteCount(dto.getVoteCount());
            movie.setVoteAverage(dto.getVoteAverage());
            movie.setSourceId(dto.getId());
            return movie;
        }).collect(Collectors.toList());

        var creditsDto = sourceIds.stream().map(moviesClient::getCredits).filter(Objects::nonNull).toList();

        var actorsSourceIds = new ArrayList<>(creditsDto.stream()
                .flatMap(x -> x.getCast().stream())
                .mapToLong(ActorSummaryDto::getId)
                .mapToInt(Math::toIntExact)
                .boxed()
                .collect(Collectors.toList()));
        var actorsDto = actorsSourceIds.stream().map(moviesClient::getActorDetails).toList();

        List<Actor> actors = actorsDto.stream().map(dto -> {
            Actor actor = new Actor();
            actor.setSourceId(dto.getId());
            actor.setName(dto.getName());
            if(dto.getBiography().length()>200)
                dto.setBiography(dto.getBiography().substring(0,199));
            actor.setBiography(dto.getBiography());
            actor.setProfilePath(dto.getProfilePath());
            actor.setPopularity(dto.getPopularity());
            actor.setBirthday(dto.getBirthday());
            actor.setDeathday(dto.getDeathday());
            return actor;
        }).collect(Collectors.toList());

        var companiesDto = moviesDto.stream().flatMap(x -> x.getProductionCompanies().stream()).distinct().toList();

        List<Company> companies = companiesDto.stream().map(dto -> {
            Company company = new Company();
            company.setSourceId(dto.getId());
            company.setName(dto.getName());
            company.setOriginCountry(company.getOriginCountry());
            return company;
        }).collect(Collectors.toList());

        data.getGenres().saveAll(genres);
        data.getMovies().saveAll(movies);
        data.getActors().saveAll(actors);
        data.getLanguages().saveAll(languages);
        data.getCompanies().saveAll(companies);
    }
}