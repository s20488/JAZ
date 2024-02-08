package org.example;

import org.example.model.Person;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
public class PeopleCleaner {

    public List<Person> getPeopleWithBooksOnly(List<Person> people){

        return people.stream() //przetwarzanie danych
                .filter(person -> !person.getBooks().isEmpty()) //filtrowanie tylko tych osób, które mają książkę
                .collect(Collectors.toList());
    }
}