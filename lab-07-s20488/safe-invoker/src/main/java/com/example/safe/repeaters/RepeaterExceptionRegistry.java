package com.example.safe.repeaters;

import java.util.HashMap;
import java.util.Map;

public class RepeaterExceptionRegistry  implements IRepeaterExceptionRegistry{
    private final static RepeaterExceptionRegistry instance;
    static{
        instance = new RepeaterExceptionRegistry();
    }

    public static RepeaterExceptionRegistry getInstance(){ return instance; }

    private final Map<String, RegistryEntry> registry = new HashMap<>();

    private RepeaterExceptionRegistry(){}

    @Override
    public void add(Class<? extends Throwable> exceptionClass, int retries, long delay) {
        String exceptionName = exceptionClass.getName();

        RegistryEntry entry = new RegistryEntry(exceptionName, delay, retries);

        registry.put(exceptionName, entry);
    }

    @Override
    public  RegistryEntry EntryFor(Throwable exception) {
        String exceptionName = exception.getClass().getName();

        return registry.getOrDefault(exceptionName, RegistryEntry.Default(exception));
    }
}
