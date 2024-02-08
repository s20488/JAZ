package com.westeros.moviesclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MoviesClientSettings implements IMoviesClientSettings {
    private String apiKey;
    private String baseUrl;
    private int apiVersion;

    public MoviesClientSettings(
            @Value("${themoviedb.api.key}") String apiKey,
            @Value("${themoviedb.api.host}") String baseUrl,
            @Value("${themoviedb.api.version}") int apiVersion) {

        this.apiKey = "b90b096c9f824efbd08ae010ded56cea";
        this.baseUrl = baseUrl;
        this.apiVersion = apiVersion;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    @Override
    public int getApiVersion() {
        return apiVersion;
    }
}
