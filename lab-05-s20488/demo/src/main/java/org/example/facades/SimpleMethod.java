package org.example.facades;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class SimpleMethod implements IMethodFacade {

    Method method;

    public SimpleMethod(Method method) {
        this.method = method;
    }

    @Override
    public boolean isPublic() {
        return Modifier.isPublic(method.getModifiers());
    }

    @Override
    public boolean paramCountEquals(int n) {
        return method.getParameterCount() == n;
    }

    @Override
    public boolean startsWith(String prefix) {
        return method.getName().startsWith(prefix);
    }

    @Override
    public boolean isVoid() {
        return  method.getAnnotatedReturnType().getType().getTypeName().contains("void");
    }

    @Override
    public boolean isSetter() {
        return this.isPublic() && this.paramCountEquals(1) && this.startsWith("set") && this.isVoid();
    }

    @Override
    public boolean isGetter() {
        return this.isPublic() && this.paramCountEquals(0) && (this.startsWith("get") || this.startsWith("is")) && !this.isVoid();
    }

    @Override
    public String getFieldName() {
        String name;
        if(this.isSetter() ) {
            name = method.getName().replaceFirst("^set",
                    Arrays.stream(method.getAnnotatedParameterTypes())
                            .toList()
                            .get(0)
                            .getType()
                            .getTypeName()
                            .contains("boolean")
                            ? "is" : ""
            );
        } else if (this.isGetter()){
            name = this.isGetter() ? method.getName().replaceFirst("^get", "") : null;
        } else {
            name = null;
        }
        name = (name != null ? name.substring(0, 1).toLowerCase() + name.substring(1) : null);
        return name;
    }

    @Override
    public Method GetUnderlyingMethod() {
        return method;
    }
}
