package org.example.queries.filters;

import org.example.model.Person;
import org.example.queries.search.SearchParameters;

import java.util.List;

public class ByAgeToFilter implements IFilterPeople {
    private SearchParameters searchParams;

    @Override
    public void setSearchParameters(SearchParameters searchParams) {
        this.searchParams = searchParams;
    }

    @Override
    public boolean canFilter() {
        return searchParams.getAgeTo() > 0;
    }

    @Override
    public List<Person> filter(List<Person> listOfPerson) {
        return listOfPerson.stream()
                .filter(p->p.getAge() <= searchParams.getAgeTo())
                .toList();
    }
}