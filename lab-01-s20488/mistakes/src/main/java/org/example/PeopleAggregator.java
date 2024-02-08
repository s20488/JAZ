package org.example;

import org.example.model.Gender;
import org.example.model.Person;
import org.example.model.Samples;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PeopleAggregator {

    public Map<Gender, List<Person>> aggregatePeopleByGender(){
        Map<Gender, List<Person>> aggregatedByGender = new HashMap<>();

        //należy stworzyć dwie oddzielne listy dla kobiet i mężczyzn
        List<Person> males = new ArrayList<>();
        List<Person> females = new ArrayList<>();

        aggregatedByGender.put(Gender.MALE, males);
        aggregatedByGender.put(Gender.FEMALE, females);

        for (Person person : Samples.getSampleListOfPeople()) {
            if (person.getGender() == Gender.MALE)
                males.add(person);
            if (person.getGender() == Gender.FEMALE)
                females.add(person);
        }

        return aggregatedByGender;
    }
}
