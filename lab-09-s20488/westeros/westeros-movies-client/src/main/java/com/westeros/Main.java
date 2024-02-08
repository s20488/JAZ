package com.westeros;

import com.westeros.moviesclient.*;
import com.westeros.moviesclient.contract.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args){

        IMoviesClientSettings settings = new MoviesClientSettings("api_key", "api.themoviedb.org", 3);
        IMoviesClient moviesClient = new MoviesClient(settings);
        var dictClient = new MoviesDictionariesClient(settings);
        var tst = dictClient.getLanguages();
        tst.stream().forEach(x->System.out.println(x.getEnglishName()));
        var countries = dictClient.getCountries();
        var genres = dictClient.getGenres();
        CheckItOut(moviesClient);
    }

    public static void CheckItOut(IMoviesClient moviesClient) {
        var from = LocalDate.now().minusMonths(1);
        var to = LocalDate.now();

        /**
         * pobieram pierwszą strone wyników filmów które zostały wyprodukowane w zeszłym miesiącu
         */
        PagedResultDto result = moviesClient.getByDateRange(from, to);
        var movies = result.getResults();

        /**
         * pobieram wszystkie strony wyników
         */
        for (int page = 1; page <= result.getTotalPages(); page++){
            movies.addAll(moviesClient.getByDateRange(from, to, page).getResults());
        }
        var detailedMovies = new ArrayList<MovieDto>();
        var allCredits = new ArrayList<CreditsDto>();

        for (var movie : movies)
        {
            /**
             * dla każdego filmu pobieram jego szczegóły
             */
            MovieDto detailedMovie = moviesClient.getMovie(movie.getId());
            detailedMovies.add(detailedMovie);

            /**
             * dla każdego filmu pobieram informacje o zespole i aktorach, który tworzył dany film
             */
            CreditsDto credits = moviesClient.getCredits(movie.getId());
            allCredits.add(credits);

            /**
             * dla każdego aktora pobieram jego szczegółowe informacje
             */
            for (var actorSummary :
                    credits.getCast()) {
                ActorDto detailedActor = moviesClient.getActorDetails(actorSummary.getId());
            }
        }
    }
}
