package org.example.validation.rules;

import org.example.validation.annotations.NotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class NotNullValidationRule extends ValidationRule{

    protected   boolean isInvalid(Object obj, Field field) throws IllegalAccessException {
        return field.get(obj) == null;
    }

    protected  Annotation getAnnotation(Field field) {
        return field.getAnnotation(NotNull.class);
    }

    protected  boolean isAnnotationPresent(Field field) {
        return field.isAnnotationPresent(NotNull.class);
    }

    protected  String prepareMessage(Field field ){
        return field.getAnnotation(NotNull.class).message();
    }
}