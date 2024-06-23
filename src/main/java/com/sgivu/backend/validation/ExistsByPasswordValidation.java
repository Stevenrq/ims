package com.sgivu.backend.validation;

import com.sgivu.backend.service.UserService;
import com.sgivu.backend.validation.annotation.ExistsByPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ExistsByPasswordValidation implements ConstraintValidator<ExistsByPassword, String> {

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (userService == null) {
            return true;
        }
        return !userService.existsByPassword(value);
    }
}
