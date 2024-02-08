package org.example;


import org.example.facades.IClassFacade;
import org.example.facades.IMethodFacade;
import org.example.facades.SimpleClass;
import org.example.facades.SimpleMethod;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class Application {

    public static void main(String[] args) throws Exception {

        Class<Subject> subjectClass = Subject.class;
        firstExerciseForMethodFacades(subjectClass);
        secondExerciseForClassFacades();
    }



    private static void firstExerciseForMethodFacades(Class<Subject> subjectClass) throws Exception {

        /**
         * Dzisiejsze zadanie związane będzie ze wzorcem fasady.
         * Nałożymy tzw "fasadę" na api metody.
         * Zacznijmy od wyciągnięcia kilku defnicji metod z klasy Subject
         */
        var setSomethingMethod = subjectClass.getDeclaredMethod("setSomething", Object.class);
        var emptyMethod = subjectClass.getDeclaredMethod("emptyMethod");
        var getterMethod = subjectClass.getDeclaredMethod("getNumber");
        var setterMethod = subjectClass.getDeclaredMethod("setNumber", int.class);

        /**
         * Utwórz interfejs o nazwie IMethodFacade oraz jego implementację w postaci klasy SimpleMethod
         */
        IMethodFacade simplePrivateMethod = new SimpleMethod(setSomethingMethod);
        IMethodFacade simplePublicMethod = new SimpleMethod(emptyMethod);
        IMethodFacade getterFacade = new SimpleMethod(getterMethod);
        IMethodFacade setterFacade = new SimpleMethod(setterMethod);

        /**
         * Teraz zaczniemy powoli dodawać deklarację nowych metod w interfejsie
         * oraz ich implementację w klasie SimlpeMethod:
         * isPublic - sprawdza czy methoda jest prywatna czy publiczna
         */
        if(simplePrivateMethod.isPublic()) throw new Exception("metoda setSomething jest prywatna");
        if(!simplePublicMethod.isPublic()) throw new Exception("metoda emptyMethod jest publiczna");

        /**
         * paramsCountEquals - sprawdza czy metoda ma zadaną liczbę parametrów
         */
        if(!simplePrivateMethod.paramCountEquals(1) || !simplePublicMethod.paramCountEquals(0)) throw new Exception("metoda nie sprawdza ilości parametrów");

        /**
         * startsWith - sprawdza czy nazwa metody posiada zadany prefix
         */
        if(!simplePrivateMethod.startsWith("set"))throw new Exception("prefix metody setSomething jest źle sprawdzany");

        /**
         * isVoid - sprawdza czy metoda jest typu void
         */
        if(!simplePrivateMethod.isVoid()) throw new Exception("metoda setSomething jest typu void");
        if(!simplePublicMethod.isVoid()) throw new Exception("metoda emptyMethod nie jest typu void");

        /**
         * isSetter - sprawdza czy metoda jest setterem tzn.:
         *      -> public
         *      -> void
         *      -> zaczyna się od 'set'
         *      -> przyjmuje tylko jeden parametr
         *
         * isGetter - sprawdz czy metoda jest getterem tzn.:
         *      -> public
         *      -> nie jest typu void
         *      -> zaczyna się od get lub is (w przypadku boolean)
         *      -> nie posiada parametrów
         */
        if(simplePrivateMethod.isSetter()) throw new Exception("metoda setSomething nie jest setterem");
        if(simplePublicMethod.isGetter())throw new Exception("metoda emptyMethod nie jest getterem");
        if(!getterFacade.isGetter())throw new Exception("metoda getNumber jest getterem");
        if(!setterFacade.isSetter())throw new Exception("metoda setNumber jest setterem");

        /**
         * getFieldName - zwraca nazwę pola dla którego metoda jest właściwością (getterem lub setterem)
         */
        if(!setterFacade.getFieldName().equals("number"))throw new Exception("pole do którego odnosi się właściwość nosi nazwę number");

        /**
         * GetUnderlyingMethod - zwraca definicję metody na którą jest nałożona fasada
         */
        Method underlyingMethod = setterFacade.GetUnderlyingMethod();
        if(!underlyingMethod.getName().equals("setNumber"))throw new Exception("nie ma dostępu do metody pierwotnej (z której jest zrobiona fasada)");
    }

    private static void secondExerciseForClassFacades() throws Exception {

        /**
         * Tutaj nołożymy fasadę na definicję klasy.
         * utwórz interfejs o nazwie IClassFacade oraz jego prostą implementację w postaci klasy SimpleClass
         */
        IClassFacade subjectClassFacade = new SimpleClass(Subject.class);

        /**
         * getPublicDeclaredMethods - zwraca fasady metod publicznych zadeklarowanych w klasie
         */
        var publicMethods = subjectClassFacade.getDeclaredMethods();
        if(publicMethods.size()!=14)throw new Exception("nie zwraca wszystkich publicznych metod");

        /**
         * getPublicGetters - zwraca fasady getterów
         *  [odkomentuj metodę areGettersFine]
         */
        var publicGetters = subjectClassFacade.getPublicGetters();
        if(!areGettersFine(publicGetters))throw new Exception("nie zwraca dobrze wszystkich getterów");

        /**
         * getPublicSetters - zwraca fasady setterów
         *  [odkomentuj metodę areSettersFine]
         */
        var publicSetters = subjectClassFacade.getPublicSetters();
        if(!areSettersFine(publicSetters))throw new Exception("nie zwraca dobrze wszystkich setterów");

        /**
         * getFieldsForPublicProperties - zwraca listę pól klasy (List<Field>), do których są gettery i settery (oba razem)
         *  [odkomentuj metodę areFieldsFine]
         */
        var fieldsWithProperties = subjectClassFacade.getFieldsForPublicProperties();
        if(!areFieldsFine(fieldsWithProperties)) throw new Exception("nie zwraca dobrze wszystkich pól do których jest setter i getter (jednocześnie oba)");
    }

    private static boolean areGettersFine(List<IMethodFacade> publicGetters) {
        return publicGetters
                .stream()
                .map(y -> y.GetUnderlyingMethod().getName())
                .toList().containsAll(List.of("getStatus", "getNumber", "isDone", "getName"))
                && publicGetters.size()==4
                ;
    }

    private static boolean areSettersFine(List<IMethodFacade> publicSetters) {
        return publicSetters
                .stream()
                .map(y -> y.GetUnderlyingMethod().getName())
                .toList().containsAll(List.of("setName","setNumber","setDone"))
                && publicSetters.size()==3;
    }

    private static boolean areFieldsFine(List<Field> fieldsWithProperties) {
        return fieldsWithProperties.stream()
                .map(x -> x.getName())
                .toList().containsAll(List.of("name", "number", "isDone")) && fieldsWithProperties.size() == 3;
    }
}