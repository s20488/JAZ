package org.example.validation;

import org.example.validation.rules.ICheckValidationRule;

import java.util.ArrayList;
import java.util.List;

public class Validator {
    List<ICheckValidationRule> rules = new ArrayList<>();
    public <T> ValidationResult<T> validate(T sampleObject) {
        var validationResult = new ValidationResult<T>();
        validationResult.setValidatedObject(sampleObject);
        rules.stream().forEach(rule->rule.validate(validationResult));
        return validationResult;
    }

    public Validator addRule(ICheckValidationRule validationRule) {
        rules.add(validationRule);
        return this;
    }
}