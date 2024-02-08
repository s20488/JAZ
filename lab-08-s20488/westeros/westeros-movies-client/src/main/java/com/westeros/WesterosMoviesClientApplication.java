package com.westeros;

import com.westeros.moviesclient.IMoviesClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WesterosMoviesClientApplication implements CommandLineRunner {


    IMoviesClient client;

    public WesterosMoviesClientApplication(IMoviesClient client) {
        this.client = client;
    }

    public static void main(String[] args) {
        SpringApplication.run(WesterosMoviesClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Main.CheckItOut(client);
    }
}
