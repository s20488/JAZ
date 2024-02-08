package com.example.safe;


import com.example.safe.repeaters.IRepeater;

public class SafeInvoker implements SafeInvoking{
    private final IRepeater repeater;

    public SafeInvoker(IRepeater repeater) {
        this.repeater = repeater;
    }


    @Override
    public InvokerResult SafeInvoke(NotSafeAction action) {
        try{
            action.execute();
        }catch (Exception exception){
            repeater.For(exception).waiting().retry();
            if(repeater.shouldRetry())
                return SafeInvoke(action);
            else return InvokerResult.error(exception);
        }
        return InvokerResult.success();
    }
}
