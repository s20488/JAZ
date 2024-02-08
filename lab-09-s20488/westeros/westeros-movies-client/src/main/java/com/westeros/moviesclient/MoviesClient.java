package com.westeros.moviesclient;

import com.westeros.moviesclient.contract.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;

@Component
public class MoviesClient implements IMoviesClient {

    RestTemplate restClient;
    String baseUrl;
    String apiKey;
    int version;
    private final IMoviesClientSettings _settings;

    public MoviesClient(IMoviesClientSettings settings) {
        restClient = new RestTemplate();
        this.baseUrl=settings.getBaseUrl();
        this.apiKey= settings.getApiKey();
        this.version= settings.getApiVersion();
        _settings=settings;
    }

    @Override
    public PagedResultDto getByDateRange(LocalDate from, LocalDate to) {
        return getByDateRange(from, to, 1);
    }

    @Override
    public PagedResultDto getByDateRange(LocalDate from, LocalDate to, int page) {
        var url = getDiscoverUriBuilder(from, to)
                .queryParam("page", page)
                .build()
                .toUriString();
        return restClient.getForObject(url, PagedResultDto.class);
    }

    private UriComponentsBuilder getDiscoverUriBuilder(LocalDate from, LocalDate to) {
        return _settings.getUrlBuilder()
                .pathSegment("discover")
                .pathSegment("movie")
                .queryParam("primary_release_date.gte", from)
                .queryParam("primary_release_date.lte", to);
    }

    @Override
    public MovieDto getMovie(int id) {
        String url = _settings.getUrlBuilder()
                .pathSegment("movie", id+"")
                .build()
                .toUriString();
        var response = restClient.getForEntity(url, MovieDto.class).getBody();
        return response;
    }

    @Override
    public CreditsDto getCredits(int id) {
        var url = _settings.getUrlBuilder()
                .pathSegment("movie", ""+id, "credits")
                .build().toUriString();

        return restClient.getForObject(url, CreditsDto.class);
    }

    @Override
    public ActorDto getActorDetails(int id) {
        var url = _settings.getUrlBuilder()
                .pathSegment("person", id+"")
                .build()
                .toUriString();

        return restClient.getForObject(url, ActorDto.class);
    }
}
