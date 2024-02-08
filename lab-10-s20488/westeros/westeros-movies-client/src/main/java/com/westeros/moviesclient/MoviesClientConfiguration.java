package com.westeros.moviesclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class MoviesClientConfiguration {

    //@Bean
    public IMoviesClientSettings getSettings(
            @Value("${themoviedb.api.key}") String apiKey,
            @Value("${themoviedb.api.host}") String host,
            @Value("${themoviedb.api.version}") int apiVersion){
        return new MoviesClientSettings();
    }
}
