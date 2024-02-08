package org.example.validation.rules;

import org.example.validation.annotations.Regex;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class RegexValidationRule extends ValidationRule{

    protected boolean isInvalid(Object obj, Field field) throws IllegalAccessException {
        var regex = field.getAnnotation(Regex.class);
        return !field.get(obj).toString().matches(regex.pattern());
    }

    protected Annotation getAnnotation(Field field) {
        return field.getAnnotation(Regex.class);
    }

    protected boolean isAnnotationPresent(Field field) {
        return field.isAnnotationPresent(Regex.class);
    }

    @Override
    protected String prepareMessage(Field field) {
        return field.getAnnotation(Regex.class).message();
    }
}