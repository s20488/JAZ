package org.example.queries.filters;

import org.example.model.Person;
import org.example.queries.search.SearchParameters;

import java.util.List;

public class ByNameFilter implements IFilterPeople {
    private SearchParameters searchParams;

    @Override
    public void setSearchParameters(SearchParameters searchParams) {
        this.searchParams = searchParams;
    }

    @Override
    public boolean canFilter() {
        try {
            return !searchParams.getName().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Person> filter(List<Person> listOfPeople) {
        return listOfPeople.stream()
                .filter(p->p.getName().equalsIgnoreCase(searchParams.getName()))
                .toList();
    }
}