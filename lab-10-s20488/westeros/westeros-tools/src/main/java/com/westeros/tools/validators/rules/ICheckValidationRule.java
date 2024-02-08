package com.westeros.tools.validators.rules;


import com.westeros.tools.validators.ValidationResult;

public interface ICheckValidationRule {
    void validate(ValidationResult<?> validationResult);
}
