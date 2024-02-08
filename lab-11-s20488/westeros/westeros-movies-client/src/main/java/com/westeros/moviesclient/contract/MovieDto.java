package com.westeros.moviesclient.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.westeros.moviesclient.contract.dictionaries.GenreSummaryDto;
import com.westeros.moviesclient.contract.dictionaries.LanguageSummaryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto extends MovieSummaryDto{

    private boolean adult;
    @JsonProperty("backdrop_path")
    private String backdropPath;
    private int budget;
    private List<GenreSummaryDto> genres;
    private String homepage;
    @JsonProperty("original_language")
    private String originalLanguage;
    @JsonProperty("original_title")
    private String originalTitle;
    private String overview;
    private double popularity;

    @JsonProperty("poster_path")
    private String posterPath;

    @JsonProperty("production_companies")
    private List<CompanySummaryDto> productionCompanies;
    @JsonProperty("production_countries")
    private List<CountrySummaryDto> productionCountries;
    @JsonProperty("release_date")
    private LocalDate releaseDate;
    private int runtime;
    @JsonProperty("spoken_languages")
    private List<LanguageSummaryDto> spokenLanguages;
    @JsonProperty("vote_count")
    private int voteCount;
    @JsonProperty("vote_average")
    private double voteAverage;

}
