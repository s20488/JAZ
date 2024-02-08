package org.example;


import org.example.validation.ValidationResult;
import org.example.validation.Validator;
import org.example.validation.rules.ICheckValidationRule;
import org.example.validation.rules.NotNullValidationRule;
import org.example.validation.rules.RangeValidationRule;
import org.example.validation.rules.RegexValidationRule;

public class Main {
    public static void main(String[] args) throws Exception {


        /**
         * 1. W klasie SampleObject odkomentuj adnotację @NotNUll,
         *    Utwórz adnotacje @NotNull w pakiecie annotations
         */
        createValidationRuleForNotNullAnnotation();

        /**
         * 2. W klasie SampleObject odkomentuj adnotację @Range,
         *    Utwórz adnotacje @Range w pakiecie annotations
         */
        createValidationRuleForRangeAnnotation();

        /**
         * 3. W klasie SampleObject odkomentuj adnotację @Regex,
         *    Utwórz adnotacje @Regex w pakiecie annotations
         */
        createValidationRuleForRegexAnnotation();

        /**
         * 4. Zaimplementuj klasę Validator
         */
        implementaValidatorClass();

    }




    private static void createValidationRuleForNotNullAnnotation() throws Exception {
        var sample = new SampleObject(null, null,-1);
        ValidationResult<SampleObject> validationResult = new ValidationResult<>();
        validationResult.setValidatedObject(sample);
        validationResult.setValid(true);

        /**
         * Utwórz nowy interfejs o nazwie ICheckValidationRule
         * oraz jego implementację w postaci klasy NotNullValidationRule.
         *
         * Interfejs posiada jedną metodę validate, która jako parametr dostaje wyniki walidacji,
         * które podczas procesu walidacji będzie modyfikować;
         */

        ICheckValidationRule notNullRule = new NotNullValidationRule();
        notNullRule.validate(validationResult);

        /**
         * obiekt nie jest prawidłowy
         */
        if(validationResult.isValid()) throw new Exception("Obiekt posiada pola które są ustawione na null, a nie powinny być.");

        /**
         * nieprawidłowe pola to name i email
         */
        var notValidFieldNames = validationResult.getNotValidFields().keySet();
        if(notValidFieldNames.size()!=2 || !notValidFieldNames.contains("email")|| !notValidFieldNames.contains("name"))
            throw new Exception("Pola które są null to name i email - walidator powinien to wychwycić");

        /**
         * wiadomości o błedzie
         */
        var validationMessagesForName = validationResult.getNotValidFields().get("name")
                .stream()
                .findFirst()
                .orElse(null);
        var validationMessagesForEmail = validationResult.getNotValidFields().get("email")
                .stream()
                .findFirst()
                .orElse(null);
        if(!validationMessagesForName.equalsIgnoreCase("pole jest null")
        || !validationMessagesForEmail.equalsIgnoreCase("field is null"))
            throw new Exception("złe wiadomości o błędzie");

    }

    private static void createValidationRuleForRangeAnnotation() throws Exception {
        var sample = new SampleObject("", "",-1);
        ValidationResult<SampleObject> validationResult = new ValidationResult<>();
        validationResult.setValidatedObject(sample);
        validationResult.setValid(true);

        /**
         * Dodaj drugą implemenatcję interfejsu ICheckValidationRule dla adnotacji Range
         *
         */
        ICheckValidationRule rangeValidationRule = new RangeValidationRule();
        rangeValidationRule.validate(validationResult);

        /**
         * obiekt nie jest prawidłowy
         */
        if(validationResult.isValid()) throw new Exception("Obiekt posiada pola które są poza zakresem");

        /**
         * nieprawidłowe pola to name i email
         */
        var notValidFieldNames = validationResult.getNotValidFields().keySet();
        if(notValidFieldNames.size()!=1 || !notValidFieldNames.contains("number"))
            throw new Exception("Pole które jest poza zakresem to number - walidator powinien to wychwycić");

        /**
         * wiadomości o błedzie
         */
        var validationMessagesForNumber = validationResult.getNotValidFields().get("number")
                .stream()
                .findFirst()
                .orElse(null);

        if(!validationMessagesForNumber.equalsIgnoreCase("number is out of range [%d,%d]" .formatted(0, 10)))
            throw new Exception("złe wiadomości o błędzie");

    }

    private static void createValidationRuleForRegexAnnotation() throws Exception {
        var sample = new SampleObject("", "jakis napis",-1);
        var validationResult = new ValidationResult<SampleObject>();
        validationResult.setValidatedObject(sample);
        validationResult.setValid(true);

        /**
         * Dodaj drugą implemenatcję interfejsu ICheckValidationRule dla adnotacji Regex
         *
         */
        ICheckValidationRule regexValidationRule = new RegexValidationRule();
        regexValidationRule.validate(validationResult);

        /**
         * obiekt nie jest prawidłowy
         */
        if(validationResult.isValid()) throw new Exception("Obiekt posiada pola które jest nie poprawnego formatu regex");

        /**
         * nieprawidłowe pola to name i email
         */
        var notValidFieldNames = validationResult.getNotValidFields().keySet();
        if(notValidFieldNames.size()!=1 || !notValidFieldNames.contains("email"))
            throw new Exception("pole email ma źle wpisany email - walidator powinien to wychwycić");

        /**
         * wiadomości o błedzie
         */
        var validationMessagesForNumber = validationResult.getNotValidFields().get("email")
                .stream()
                .findFirst()
                .orElse(null);

        if(!validationMessagesForNumber.equalsIgnoreCase("email should be in correct format"))
            throw new Exception("złe wiadomości o błędzie");

    }

    private static void implementaValidatorClass() throws Exception {

        var sampleObject = new SampleObject(null, "jakis napis",-1);
        Validator validator = new Validator();

        /**
         * dodaj reguły walidacji do walidatora
         */
        validator.addRule(new NotNullValidationRule())
                .addRule(new RangeValidationRule())
                .addRule(new RegexValidationRule());

        /**
         * Zaimplementuj metodę validate w klasie Validator,
         * tak aby obiekt został sprawdzony przez wszystkie reguły walidacji
         * oraz zwrócił poprawny wynik walidacji,
         * w którym sprawdzimy czy obiekt jest poprawny, srawdzimy jakie pola są niepoprawne
         * oraz wiadomości o błędach walidacji
         */
        ValidationResult<SampleObject> result = validator.validate(sampleObject);

        var resultNames = result.getNotValidFields().keySet();
        if(resultNames.size()!=3)
            throw new Exception("3 pola w klasie są złej wartości - walidator powinien to wychwycić");

    }

}