package com.westeros.tools.validators;

import com.westeros.tools.validators.rules.ICheckValidationRule;

import java.util.ArrayList;
import java.util.List;

public class Validator {

    List<ICheckValidationRule> rules = new ArrayList<>();
    public <T> ValidationResult<T> validate(T sampleObject) {
        var result = new ValidationResult<T>();
        result.setValid(true);
        result.setValidatedObject(sampleObject);
        rules.forEach(rule->rule.validate(result));
        return result;
    }

    public Validator addRule(ICheckValidationRule rule) {
        rules.add(rule);
        return this;
    }
}
