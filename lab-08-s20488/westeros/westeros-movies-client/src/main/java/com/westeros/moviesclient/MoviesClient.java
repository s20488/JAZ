package com.westeros.moviesclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.westeros.moviesclient.contract.ActorDto;
import com.westeros.moviesclient.contract.CreditsDto;
import com.westeros.moviesclient.contract.MovieDto;
import com.westeros.moviesclient.contract.PagedResultDto;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

public class MoviesClient implements IMoviesClient {

    RestTemplate restClient;
    String baseUrl;
    String apiKey;
    int version;

    public MoviesClient(IMoviesClientSettings settings) {
        restClient = new RestTemplate();
        this.baseUrl = settings.getBaseUrl();
        this.apiKey = settings.getApiKey();
        this.version = settings.getApiVersion();
    }

    @Override
    public PagedResultDto getByDateRange(LocalDate from, LocalDate to) {
        String url = baseUrl + "/" + version + "/discover/movie?primary_release_date.gte=" + from + "&primary_release_date.lte=" + to + "&api_key=" + apiKey;
        System.out.println(url);
        return restClient.getForEntity(url, PagedResultDto.class).getBody();
    }

    @Override
    public PagedResultDto getByDateRange(LocalDate from, LocalDate to, int page) {
        String url = baseUrl + "/" + version + "/discover/movie?page=" + page + "&primary_release_date.gte=" + from + "&primary_release_date.lte=" + to + "&api_key=" + apiKey;
        return restClient.getForEntity(url, PagedResultDto.class).getBody();
    }

    @Override
    public MovieDto getMovie(int id) {
        String url = baseUrl + "/" + version + "/movie/" + id + "?api_key=" + apiKey;
        return restClient.getForEntity(url, MovieDto.class).getBody();
    }

    @Override
    public CreditsDto getCredits(int id) {
        String url = baseUrl + "/" + version + "/movie/" + id + "/credits?api_key=" + apiKey;
        return restClient.getForEntity(url, CreditsDto.class).getBody();
    }

    @Override
    public ActorDto getActorDetails(int id) {
        String url = baseUrl + "/" + version + "/person/" + id + "?api_key=" + apiKey;
        return restClient.getForEntity(url, ActorDto.class).getBody();
    }
}
