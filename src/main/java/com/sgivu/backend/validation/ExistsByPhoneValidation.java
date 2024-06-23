package com.sgivu.backend.validation;

import com.sgivu.backend.service.PersonService;
import com.sgivu.backend.validation.annotation.ExistsByPhone;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ExistsByPhoneValidation implements ConstraintValidator<ExistsByPhone, Long> {

    @Autowired
    private PersonService personService;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (personService == null) {
            return true;
        }
        return !personService.existsByPhone(value);
    }
}
