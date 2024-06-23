package com.sgivu.backend.validation;

import com.sgivu.backend.service.PersonService;
import com.sgivu.backend.validation.annotation.ExistsByEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ExistsByEmailValidation implements ConstraintValidator<ExistsByEmail, String> {

    @Autowired
    private PersonService personService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (personService == null) {
            return true;
        }
        return !personService.existsByEmail(value);
    }
}
