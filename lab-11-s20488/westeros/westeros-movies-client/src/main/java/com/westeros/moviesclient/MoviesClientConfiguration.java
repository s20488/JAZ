package com.westeros.moviesclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class MoviesClientConfiguration {

    //@Bean
    public IMoviesClientSettings getSettings(
            @Value("b90b096c9f824efbd08ae010ded56cea") String apiKey,
            @Value("${themoviedb.api.host}") String host,
            @Value("${themoviedb.api.version}") int apiVersion){
        return new MoviesClientSettings();
    }
}
