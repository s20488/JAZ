package com.westeros.moviesclient;

import com.westeros.moviesclient.contract.CountrySummaryDto;
import com.westeros.moviesclient.contract.dictionaries.CountryDto;
import com.westeros.moviesclient.contract.dictionaries.GenreSummaryDto;
import com.westeros.moviesclient.contract.dictionaries.LanguageSummaryDto;

import java.util.List;

public interface IMoviesDictionariesClient {

    List<LanguageSummaryDto> getLanguages();
    List<GenreSummaryDto> getGenres();
    List<CountryDto> getCountries();
}
