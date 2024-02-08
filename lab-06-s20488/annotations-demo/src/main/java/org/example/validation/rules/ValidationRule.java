package org.example.validation.rules;

import org.example.validation.ValidationResult;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.stream.Stream;

public abstract class ValidationRule implements ICheckValidationRule{

    @Override
    public void validate(ValidationResult<?> validationResult) {

        Class<?> clazz = validationResult.getValidatedObject().getClass();
        var obj = validationResult.getValidatedObject();
        Stream.of(clazz.getDeclaredFields())
                .filter(this::isAnnotationPresent)
                .forEach((field) ->{
                            try{
                                field.setAccessible(true);
                                if(isInvalid(obj, field))
                                    prepareInvalidResult(validationResult, field);
                            }catch(Exception x) {
                                x.printStackTrace();
                            }
                        }
                );

    }

    private  void prepareInvalidResult(ValidationResult<?> validationResult, Field field) {
        validationResult.setValid(false);
        validationResult.getNotValidFields()
                .putIfAbsent(field.getName(), new ArrayList<String>());
        validationResult.getNotValidFields()
                .get(field.getName())
                .add(prepareMessage(field));
    }

    protected abstract boolean isInvalid(Object obj, Field field) throws IllegalAccessException;
    protected abstract Annotation getAnnotation(Field field);
    protected abstract boolean isAnnotationPresent(Field field);
    protected abstract   String prepareMessage(Field field );
}