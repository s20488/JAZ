package org.example.queries;

import org.example.model.Person;
import org.example.queries.calculations.ICalculate;
import org.example.queries.filters.IFilterPeople;
import org.example.queries.paging.ICutToPage;
import org.example.queries.results.FunctionResult;
import org.example.queries.results.Results;
import org.example.queries.search.FunctionsParameters;
import org.example.queries.search.Page;
import org.example.queries.search.SearchParameters;

import java.util.ArrayList;
import java.util.List;

public class QueryProcessor {
    private List<IFilterPeople> filter = new ArrayList<>();
    private List<ICalculate> calculators = new ArrayList<>();
    private ICutToPage pageCutter;

    public QueryProcessor addFilter(IFilterPeople filter) {
        this.filter.add(filter);
        return this;
    }

    public QueryProcessor addCalculator(ICalculate calculator) {
        this.calculators.add(calculator);
        return this;
    }

    public void addPageCutter(ICutToPage pageCutter) {
        this.pageCutter = pageCutter;
    }

    public Results GetResults(SearchParameters params, List<Person> data){
        Results results = new Results();
        int counter = 0;
        int onSinglePage = params.getPage().getSize();
        int amountItems = data.size();

        List<FunctionResult> functionResults = new ArrayList<>();
        for (FunctionsParameters param : params.getFunctions()) {
            double value = calculators.get(0).calculate(param, data);
            FunctionResult fResult = new FunctionResult();
            fResult.setFieldName(param.getFieldName());
            fResult.setFunction(param.getFunction());
            fResult.setValue(value);
            functionResults.add(fResult);
        }

        List<Person> filteredData = data;
        for (IFilterPeople filterPeople : filter) {
            filterPeople.setSearchParameters(params);
            if (filterPeople.canFilter()) {
                filteredData = filterPeople.filter(filteredData);
            }
        }

        while (amountItems > onSinglePage) {
            amountItems -= onSinglePage;
            counter++;
        }

        results.setItems(filteredData);
        results.setFunctionResults(functionResults);
        results.setCurrentPage(counter);
        results.setPages(counter);

        return results;
    }
}