package com.example;


import com.example.safe.SafeInvoker;
import com.example.safe.repeaters.Repeater;
import com.example.safe.repeaters.RepeaterExceptionRegistry;

import java.util.Random;

public class Main {

    public static void main(String[] args){

        /**
         * W rejestrze o błędach zapisujemy konfigurację o tym ile razy błąd może wystąpić
         * i w jakim odstępie program ma poczekać na kolene wywołanie operacji
         */
        var registry = RepeaterExceptionRegistry.getInstance();
        registry.add(Exception.class, 2, 1000);

        /**
         * metoda EntryFor ma zwrócić nam konkretną konfigurację,
         * z której możemy odczytać dane o konfiguracji
         */
        var entry = registry.EntryFor(new Exception());
        var exceptionName = entry.exceptionName();
        var retriesMaxCount = entry.retriesCount();
        var timeInterval = entry.delay();


        /**
         * Tworzę obiekt Repeater który jest potrzebny dla klasy SafeInvoker
         */
        var repeater = new Repeater(registry);

        /**
         * SafeInvoker próbuje wykonać wielokrotnie metodę 'sometimesItWorksFine'
         */
        var safeInvoker = new SafeInvoker(repeater);

        var result = safeInvoker.SafeInvoke(Main::sometimesItWorksFine);
         if(!result.isSuccess())
             result.onUnhandledException(exception-> {
                 System.out.println("Nie udało się wykonać akcji :(");
                 exception.printStackTrace();
             });

        /**
         * Jeśli Twoja implementacja działa
         * to odkomentuj kod z klasy SafeInvokerConfiguration
         * i przejdź do klasy SafeInvokerApplication
         * i zapoznaj się z tym jak Springowi dostarczymy obiekt klasy SafeInvoker
         */

    }

    static void sometimesItWorksFine() throws Exception {
        var random = new Random().nextInt(11);
        if(random<1) {
            System.out.println("DZIAŁAM !!");
            return;
        }
        throw new Exception();
    }
}
