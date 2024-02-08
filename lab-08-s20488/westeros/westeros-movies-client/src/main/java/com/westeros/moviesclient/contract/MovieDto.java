package com.westeros.moviesclient.contract;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

public class MovieDto extends MovieSummaryDto{

    private boolean adult;
    private String backdropPath;
    private int budget;
    private List<GenreSummaryDto> genres;
    private String homepage;
    @JsonProperty("original_language")
    private String originalLanguage;
    private String originalTitle;
    private String overview;
    private double popularity;
    private String posterPath;
    private List<CompanySummaryDto> productionCompanies;
    private List<CountrySummaryDto> productionCountries;
    private LocalDate releaseDate;
    private int runtime;
    private List<LanguageSummaryDto> spokenLanguages;
    private int voteCount;
    private double voteAverage;

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public List<GenreSummaryDto> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreSummaryDto> genres) {
        this.genres = genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public List<CompanySummaryDto> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List<CompanySummaryDto> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public List<CountrySummaryDto> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(List<CountrySummaryDto> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public List<LanguageSummaryDto> getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(List<LanguageSummaryDto> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }
}
