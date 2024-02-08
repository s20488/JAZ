package com.example.safe.repeaters;


public class Repeater implements IRepeater{
    String exceptionName;
    int counter;
    int retryCount;
    long delayTime;
    private final IRepeaterExceptionRegistry exceptionRegistry;

    public Repeater(IRepeaterExceptionRegistry exceptionRegistry) {
        this.exceptionRegistry = exceptionRegistry;
    }

    @Override
    public IRepeater For(Throwable exception) {
        this.exceptionName = exception.getClass().getName();
        IRepeaterExceptionRegistry.RegistryEntry entry = exceptionRegistry.EntryFor(exception);
        this.retryCount = entry.retriesCount();
        this.delayTime = entry.delay();
        this.counter = 0;

        return this;
    }

    @Override
    public void retry() {
        counter++;
    }

    @Override
    public boolean shouldRetry() {
        return counter<=retryCount;
    }

    @Override
    public IRepeater waiting() {
        try {
            Thread.sleep(delayTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }
}
