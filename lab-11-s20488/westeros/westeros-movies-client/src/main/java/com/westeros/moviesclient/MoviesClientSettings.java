package com.westeros.moviesclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MoviesClientSettings implements IMoviesClientSettings {

    @Value("${themoviedb.api.key}")
    private String apiKey;
    @Value("${themoviedb.api.host}")
    private String baseUrl;
    @Value("${themoviedb.api.version}")
    private int apiVersion;

//    public MoviesClientSettings(String apiKey, String baseUrl, int apiVersion) {
//
//        this.apiKey = apiKey;
//        this.baseUrl = baseUrl;
//        this.apiVersion = apiVersion;
//    }

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
