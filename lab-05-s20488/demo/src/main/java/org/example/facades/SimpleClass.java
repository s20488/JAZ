package org.example.facades;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class SimpleClass implements IClassFacade {
    private Class<?> clazz;

    public SimpleClass(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public List<IMethodFacade> getDeclaredMethods() {
        List<IMethodFacade> methodFacades = new ArrayList<>();
        for (Method method : clazz.getDeclaredMethods()) {
            SimpleMethod simpleMethod = new SimpleMethod(method);
            if (simpleMethod.isPublic()) {
                methodFacades.add(simpleMethod);
            }
        }
        return methodFacades;
    }

    @Override
    public List<IMethodFacade> getPublicSetters() {
        List<IMethodFacade> methodFacades = new ArrayList<>();
        for (Method method : clazz.getDeclaredMethods()) {
            SimpleMethod simpleMethod = new SimpleMethod(method);
            if (simpleMethod.isSetter()) {
                methodFacades.add(simpleMethod);
            }
        }
        return methodFacades;
    }

    @Override
    public List<IMethodFacade> getPublicGetters() {
        List<IMethodFacade> methodFacades = new ArrayList<>();
        for (Method method : clazz.getDeclaredMethods()) {
            SimpleMethod simpleMethod = new SimpleMethod(method);
            if (simpleMethod.isGetter()) {
                methodFacades.add(simpleMethod);
            }
        }
        return methodFacades;
    }

    @Override
    public List<Field> getFieldsForPublicProperties() {
        List<Field> fields = new ArrayList<>();
        List<IMethodFacade> setters = this.getPublicSetters();
        List<IMethodFacade> getters = this.getPublicGetters();

        for (Field field : clazz.getDeclaredFields()) {
            if (!(setters.stream().filter(p -> p.getFieldName().contains(field.getName())).findAny().isEmpty()
                    || getters.stream().filter(p -> p.getFieldName().contains(field.getName())).findAny().isEmpty()))
                fields.add(field);
        }
        return fields;
    }
}