package com.westeros.tools.safeinvoker;

import java.util.function.Consumer;

/**
 * Obiekt tego interfejsu będzie wykonywał akcję, która może wyrzucić błąd,
 * jeśli błąd wystąpi to tworzy obiekt Repeatera, dla konkretnego błedu,
 * i powtarza wykonanie akcji aż do skutku,
 * albo aż maksymalna ilość wystąpienia błędu zostanie osiągnięta
 */
public interface SafeInvoking {
    InvokerResult SafeInvoke(NotSafeAction action);

    record InvokerResult(Exception exception, boolean isSuccess){

        public static InvokerResult Success(){
            return new InvokerResult(null, true);
        }

        public static InvokerResult FailedWithException(Exception exception){
            return new InvokerResult(exception, false);
        }

        public void onUnhandledException(Consumer<Exception> consumer){
            if(isSuccess)return;
            consumer.accept(exception);
        }
    }
}
