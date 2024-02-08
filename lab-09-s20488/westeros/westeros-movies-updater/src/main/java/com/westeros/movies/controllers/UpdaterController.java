package com.westeros.movies.controllers;

import com.westeros.movies.updater.IUpdateMovies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

@RestController
public class UpdaterController {

    private IUpdateMovies moviesUpdater;

    @GetMapping("/updater/start")
    public String updateMovies(@RequestParam String from, @RequestParam String to) {
        LocalDate fromDate = LocalDate.parse(from);
        LocalDate toDate = LocalDate.parse(to);

        new Thread(() -> {
            moviesUpdater.updateByDateRange(fromDate, toDate);
        }).start();

        return "Update started";
    }
}