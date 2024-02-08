package com.westeros.moviesclient;

public interface IMoviesClientSettings {

    /**
     * Ten getter zwraca klucz do api https://www.themoviedb.org/
     * -> utwórz darmowe konto i sprawdź w ustawieniach konta wartość klucza
     * @return klucz do api
     */
     String getApiKey();

    /**
     * Ten getter zwraca adres bazowy do api http://api.themoviedb.org
     * @return
     */
    String getBaseUrl();

    /**
     * zwraca wersję api - domyślnie 3
     * @return
     */
    int getApiVersion();
}
