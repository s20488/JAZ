package com.westeros.movies.mappers;

import com.westeros.data.model.*;
import com.westeros.data.model.CharacterRole;
import com.westeros.moviesclient.contract.ActorDto;
import com.westeros.moviesclient.contract.ActorSummaryDto;
import com.westeros.moviesclient.contract.CompanySummaryDto;
import com.westeros.moviesclient.contract.MovieDto;
import com.westeros.moviesclient.contract.dictionaries.CountryDto;
import com.westeros.moviesclient.contract.dictionaries.GenreSummaryDto;
import com.westeros.moviesclient.contract.dictionaries.LanguageSummaryDto;
import org.springframework.stereotype.Component;

@Component
public class EntityMapper implements ICatalogMappers{

    private final IMapEntities<ActorDto, Actor> forActor;
    private final IMapEntities<ActorSummaryDto, CharacterRole> forCharacter;
    private final IMapEntities<CompanySummaryDto, Company> forCompany;
    private final IMapEntities<CountryDto, Country> forCountry;
    private final IMapEntities<GenreSummaryDto, Genre> forGenre;
    private final IMapEntities<LanguageSummaryDto, SpokenLanguage> forLanguage;
    private final IMapEntities<MovieDto, Movie> forMovie;

    public EntityMapper(IMapEntities<ActorDto, Actor> forActor, IMapEntities<ActorSummaryDto, CharacterRole> forCharacter, IMapEntities<CompanySummaryDto, Company> forCompany, IMapEntities<CountryDto, Country> forCountry, IMapEntities<GenreSummaryDto, Genre> forGenre, IMapEntities<LanguageSummaryDto, SpokenLanguage> forLanguage, IMapEntities<MovieDto, Movie> forMovie) {
        this.forActor = forActor;
        this.forCharacter = forCharacter;
        this.forCompany = forCompany;
        this.forCountry = forCountry;
        this.forGenre = forGenre;
        this.forLanguage = forLanguage;
        this.forMovie = forMovie;
    }


    @Override
    public IMapEntities<ActorDto, Actor> forActor() {
        return forActor;
    }

    @Override
    public IMapEntities<ActorSummaryDto, CharacterRole> forCharacter() {
        return forCharacter;
    }

    @Override
    public IMapEntities<CompanySummaryDto, Company> forCompany() {
        return forCompany;
    }

    @Override
    public IMapEntities<CountryDto, Country> forCountry() {
        return forCountry;
    }

    @Override
    public IMapEntities<GenreSummaryDto, Genre> forGenre() {
        return forGenre;
    }

    @Override
    public IMapEntities<LanguageSummaryDto, SpokenLanguage> forLanguage() {
        return forLanguage;
    }

    @Override
    public IMapEntities<MovieDto, Movie> forMovie() {
        return forMovie;
    }
}
