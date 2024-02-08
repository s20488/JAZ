package org.example.validation.rules;

import org.example.validation.annotations.Range;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class RangeValidationRule extends ValidationRule {

    protected   boolean isInvalid(Object obj, Field field) throws IllegalAccessException {
        var range = field.getAnnotation(Range.class);
        return field.getInt(obj)< range.min() || field.getInt(obj)> range.max();
    }

    protected Annotation getAnnotation(Field field) {
        return field.getAnnotation(Range.class);
    }

    protected boolean isAnnotationPresent(Field field) {
        return field.isAnnotationPresent(Range.class);
    }

    @Override
    protected String prepareMessage(Field field) {
        var range = field.getAnnotation(Range.class);
        return range.message().formatted(range.min(), range.max());
    }
}