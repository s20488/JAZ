package com.westeros.data.repositories;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Getter
public class WesterosDataCatalog implements ICatalogData {

    private final CompanyRepository companies;
    private final MoviesRepository movies;
    private final ActorRepository actors;
    private final CharacterRepository character;
    private final CountryRepository countries;
    private final GenreRepository genres;
    private final LanguageRepository languages;



}
