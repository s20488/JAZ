package org.example.queries.paging;

import org.example.model.Person;
import org.example.queries.search.Page;

import java.util.List;

public class PageCutter implements ICutToPage {
    @Override
    public List<Person> cut(Page page, List<Person> listOfPeople){
        long toDisplay = page.getSize();
        long toSkip = toDisplay * page.getPageNumber();
        return listOfPeople.stream()
                .skip(toSkip)
                .limit(toDisplay)
                .toList();
    }
}
