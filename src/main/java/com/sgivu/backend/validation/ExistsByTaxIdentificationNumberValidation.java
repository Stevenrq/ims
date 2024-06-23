package com.sgivu.backend.validation;

import com.sgivu.backend.service.CustomerService;
import com.sgivu.backend.validation.annotation.ExistsByTaxIdentificationNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ExistsByTaxIdentificationNumberValidation implements ConstraintValidator<ExistsByTaxIdentificationNumber, Long> {

    @Autowired
    private CustomerService customerService;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (customerService == null) {
            return true;
        }
        return !customerService.existsByTaxIdentificationNumber(value);
    }
}
