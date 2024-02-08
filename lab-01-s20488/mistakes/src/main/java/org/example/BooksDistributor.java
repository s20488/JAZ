package org.example;

import org.example.model.Book;
import org.example.model.Person;
import org.example.model.Samples;

import java.util.Random;

public class BooksDistributor {

    public void distributeBooksThroughPeople(){

        for (Person person : Samples.getSampleListOfPeople()) {

            if (!Samples.getAvailableBooks().isEmpty()) { //sprawdzanie dostępnych książek
                int index = getRandomIndex();
                Book book = Samples.getAvailableBooks().remove(index); //usunięcie książki z listy po przypisaniu jej do określonej osoby
                book.setOwner(person); //przypisywanie książki do osoby
                person.getBooks().add(book);
            }
        }
    }

    private int getRandomIndex(){
        return new Random().nextInt(Samples.getAvailableBooks().size());
    }
}







