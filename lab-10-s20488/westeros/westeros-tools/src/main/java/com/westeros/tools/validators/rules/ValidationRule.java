package com.westeros.tools.validators.rules;


import com.westeros.tools.validators.ValidationResult;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class ValidationRule implements ICheckValidationRule{
    @Override
    public void validate(ValidationResult<?> validationResult) {
        var clazz = validationResult.getValidatedObject().getClass();
        var fields = List.of(clazz.getDeclaredFields());
        fields.stream()
                .filter(field -> field.isAnnotationPresent(getAnnotationClass()))
                .forEach(field -> validateField(validationResult, field));
    }


    private void validateField(ValidationResult<?> validationResult, Field field) {
        field.setAccessible(true);
        var range = field.getAnnotation(getAnnotationClass());
        try {
            if (!checkRule(validationResult, field, range)) {
                prepareInvalidResult(validationResult, field, range);
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    private void prepareInvalidResult(ValidationResult<?> validationResult, Field field, Annotation annotation) {
        validationResult.setValid(false);
        validationResult.getNotValidFields().putIfAbsent(field.getName(), new ArrayList<>());
        validationResult.getNotValidFields().get(field.getName()).add(prepareMessage(annotation));
    }

    protected abstract String prepareMessage(Annotation annotation);
    protected abstract Class<? extends Annotation> getAnnotationClass();
    protected abstract boolean checkRule(ValidationResult<?> validationResult, Field field, Annotation annotation) throws IllegalAccessException;
}
