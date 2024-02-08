package org.example.facades;

import java.lang.reflect.Method;

public interface IMethodFacade {
    boolean isPublic();
    boolean paramCountEquals(int n);
    boolean startsWith(String prefix);
    boolean isVoid();
    boolean isSetter();
    boolean isGetter();
    String getFieldName();
    Method GetUnderlyingMethod();
}