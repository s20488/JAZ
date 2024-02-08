package com.westeros.tools.validators.rules;


import com.westeros.tools.validators.ValidationResult;
import com.westeros.tools.validators.annotations.Range;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class RangeValidationRule extends ValidationRule {

    @Override
    protected String prepareMessage(Annotation annotation) {
        var range = (Range) annotation;
        return range.message().formatted(range.min(), range.max());
    }

    @Override
    protected Class<? extends Annotation> getAnnotationClass() {
        return Range.class;
    }

    @Override
    protected boolean checkRule(ValidationResult<?> validationResult, Field field, Annotation annotation) throws IllegalAccessException {
        var range = (Range) annotation;
        return field.getInt(validationResult.getValidatedObject())<= range.max()
                && field.getInt(validationResult.getValidatedObject())>= range.min();
    }
}
