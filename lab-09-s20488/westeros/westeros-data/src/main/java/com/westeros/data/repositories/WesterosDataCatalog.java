package com.westeros.data.repositories;

import org.springframework.stereotype.Repository;

@Repository
public class WesterosDataCatalog implements ICatalogData {

    private final CompanyRepository companies;
    private final MoviesRepository movies;

    private final ActorRepository actors;

    private final GenreRepository genres;

    private final LanguageRepository languages;


    public WesterosDataCatalog(CompanyRepository companies, MoviesRepository movies, ActorRepository actors, GenreRepository genres, LanguageRepository languages) {
        this.companies = companies;
        this.movies = movies;
        this.actors = actors;
        this.genres = genres;
        this.languages = languages;
    }

    @Override
    public CompanyRepository getCompanies() {
        return companies;
    }

    @Override
    public MoviesRepository getMovies() {
        return movies;
    }

    @Override
    public ActorRepository getActors() {
        return actors;
    }

    @Override
    public GenreRepository getGenres() {
        return genres;
    }

    @Override
    public LanguageRepository getLanguages() {
        return languages;
    }
}