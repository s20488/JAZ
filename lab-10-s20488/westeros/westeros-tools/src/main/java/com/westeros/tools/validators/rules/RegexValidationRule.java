package com.westeros.tools.validators.rules;


import com.westeros.tools.validators.ValidationResult;
import com.westeros.tools.validators.annotations.Regex;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class RegexValidationRule extends ValidationRule {
    @Override
    protected String prepareMessage(Annotation annotation) {
        var regex = (Regex) annotation;
        return regex.message();
    }

    @Override
    protected Class<? extends Annotation> getAnnotationClass() {
        return Regex.class;
    }

    @Override
    protected boolean checkRule(ValidationResult<?> validationResult, Field field, Annotation annotation) throws IllegalAccessException {
        String value = (String)field.get(validationResult.getValidatedObject());
        var regex = (Regex) annotation;
        return value.matches(regex.pattern());
    }
}
