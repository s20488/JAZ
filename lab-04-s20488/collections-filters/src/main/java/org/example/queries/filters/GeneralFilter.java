package org.example.queries.filters;

import org.example.model.Person;
import org.example.queries.search.SearchParameters;

import java.util.List;
import java.util.function.Predicate;

public class GeneralFilter implements IFilterPeople {
    private SearchParameters searchParams;
    private final Predicate<SearchParameters> canFilterPredicate;
    private final DualPredicate filterDualPredicate;

    public GeneralFilter(Predicate<SearchParameters> predicate, DualPredicate filterDualPredicate) {
        this.canFilterPredicate = predicate;
        this.filterDualPredicate = filterDualPredicate;
    }

    @Override
    public void setSearchParameters(SearchParameters searchParameters) {
        this.searchParams = searchParameters;
    }

    @Override
    public boolean canFilter() {
        return canFilterPredicate.test(searchParams);
    }

    @Override
    public List<Person> filter(List<Person> listOfPeople) {
        return listOfPeople.stream()
                .filter(p -> filterDualPredicate.check(searchParams,p))
                .toList();
    }
}