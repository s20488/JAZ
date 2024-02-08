package com.westeros.diagnostics;

import com.westeros.diagnostics.runners.IDiagnose;
import com.westeros.diagnostics.service.contract.Diagnostics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;

public class TheMovieDbApiConnectivityDiagnostics implements IDiagnose {
    @Override
    public String getName() {
        return "TheMovieDbApiConnectivityDiagnostics";
    }

    @Override
    public String getDescription() {
        return "This class is responsible for testing the connectivity with TheMovieDb API service.";
    }

    @Override
    public Diagnostics run() {
        Diagnostics diagnostics = new Diagnostics();
        diagnostics.setName(getName());
        diagnostics.setDescription(getDescription());

        try {
            String apiKey = System.getenv("b90b096c9f824efbd08ae010ded56cea");
            String apiHost = "api.themoviedb.org";
            String apiVersion = "3";
            String apiEndpoint = "/movie/550?api_key=" + apiKey;
            URL url = new URL("https://" + apiHost + "/" + apiVersion + apiEndpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            connection.disconnect();

            diagnostics.setSuccess(responseCode == 200);
            diagnostics.setErrorMessage(null);
        } catch (IOException e) {
            diagnostics.setSuccess(false);
            diagnostics.setErrorMessage(e.getMessage());
        }

        return diagnostics;
    }
}
