package org.example.queries.calculations;

import org.example.model.Person;
import org.example.queries.search.FunctionsParameters;

import java.util.List;
import java.util.function.Function;

public class GeneralCalculator implements ICalculate {
    private final String fieldName;
    private final Function<Person, Number> fieldGetter;

    public GeneralCalculator(String fieldName, Function<Person, Number> fieldGetter) {
        this.fieldName = fieldName;
        this.fieldGetter = fieldGetter;
    }

    @Override
    public double calculate(FunctionsParameters functionsParameters, List<Person> listOfPerson) {
        double result;
        switch (functionsParameters.getFunction()) {
            case SUM -> result = listOfPerson.stream()
                    .mapToDouble(p -> fieldGetter.apply(p).doubleValue())
                    .sum();
            case AVERAGE -> result = listOfPerson.stream()
                    .mapToDouble(p -> fieldGetter.apply(p).doubleValue())
                    .average()
                    .orElse(-1);
            case MAX -> result = listOfPerson.stream()
                    .mapToDouble(p -> fieldGetter.apply(p).doubleValue())
                    .max()
                    .orElse(-1);
            case MIN -> result = listOfPerson.stream()
                    .mapToDouble(p -> fieldGetter.apply(p).doubleValue())
                    .min()
                    .orElse(-1);
            default -> result = -1;
        }
        return result;
    }
}