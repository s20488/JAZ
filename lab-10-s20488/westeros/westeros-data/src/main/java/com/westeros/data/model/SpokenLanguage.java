package com.westeros.data.model;

import com.westeros.common.interfaces.IHaveName;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class SpokenLanguage implements IHaveName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany
    private List<Movie> movies = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpokenLanguage spokenLanguage = (SpokenLanguage) o;
        return id == spokenLanguage.id&& Objects.equals(name, spokenLanguage.name) && Objects.equals(movies, spokenLanguage.movies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, movies);
    }
}
