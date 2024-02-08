package com.westeros.data.repositories;

public interface ICatalogData {
    CompanyRepository getCompanies();
    MoviesRepository getMovies();
    ActorRepository getActors();
    GenreRepository getGenres();
    LanguageRepository getLanguages();
}
