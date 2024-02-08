package com.westeros.movies.controllers;

import com.westeros.data.repositories.ICatalogData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class UpdaterController{

    private final ICatalogData db;
//
//    public UpdaterController(ICatalogData db) {
//        this.db = db;
//    }
}
