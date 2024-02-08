package com.westeros.tools.validators.rules;


import com.westeros.tools.validators.ValidationResult;
import com.westeros.tools.validators.annotations.NotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class NotNullValidationRule extends ValidationRule {

    @Override
    protected String prepareMessage(Annotation annotation) {
        var notNull = (NotNull) annotation;
        return notNull.message();
    }

    @Override
    protected Class<? extends Annotation> getAnnotationClass() {
        return NotNull.class;
    }

    @Override
    protected boolean checkRule(ValidationResult<?> validationResult, Field field, Annotation annotation) throws IllegalAccessException {
        return field.get(validationResult.getValidatedObject())!=null;
    }
}
