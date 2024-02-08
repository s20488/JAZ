package org.example.model;

import java.util.List;
import java.util.ArrayList;

public class Person {

    private String name;
    private String surname;
    private Gender gender;
    private List<Book> books;

    public Person(String name, String surname, Gender gender) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        books = new ArrayList<Book>(); //inicjalizacja listy książek
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Book> getBooks() {
        return books;
    }
}
