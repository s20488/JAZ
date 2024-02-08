package com.westeros.moviesclient;

import com.westeros.moviesclient.contract.CountrySummaryDto;
import com.westeros.moviesclient.contract.dictionaries.CountryDto;
import com.westeros.moviesclient.contract.dictionaries.GenreSummaryDto;
import com.westeros.moviesclient.contract.dictionaries.GenresListDto;
import com.westeros.moviesclient.contract.dictionaries.LanguageSummaryDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class MoviesDictionariesClient implements IMoviesDictionariesClient{

    RestTemplate restClient;
    String baseUrl;
    String apiKey;
    int version;
    private final IMoviesClientSettings settings;

    public MoviesDictionariesClient(IMoviesClientSettings settings) {
        restClient = new RestTemplate();
        this.baseUrl=settings.getBaseUrl();
        this.apiKey= settings.getApiKey();
        this.version= settings.getApiVersion();
        this.settings=settings;
    }

    @Override
    public List<LanguageSummaryDto> getLanguages() {
        String url = settings.getUrlBuilder()
                .pathSegment("configuration", "languages")
                .build()
                .toUriString();
        return restClient.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ArrayList<LanguageSummaryDto>>() {})
                .getBody();
    }

    @Override
    public List<GenreSummaryDto> getGenres() {
        String url = settings.getUrlBuilder()
                .pathSegment("genre", "movie", "list")
                .build()
                .toUriString();
        return restClient.getForObject(url, GenresListDto.class).getGenres();
    }

    @Override
    public List<CountryDto> getCountries() {
        String url = settings.getUrlBuilder()
                .pathSegment("configuration", "countries")
                .build()
                .toUriString();
        return restClient.exchange(url,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<ArrayList<CountryDto>>() {})
                .getBody();
    }
}
