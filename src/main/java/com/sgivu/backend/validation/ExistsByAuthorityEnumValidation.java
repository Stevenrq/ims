package com.sgivu.backend.validation;

import com.sgivu.backend.model.AuthorityEnum;
import com.sgivu.backend.service.AuthorityService;
import com.sgivu.backend.validation.annotation.ExistsByAuthorityEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ExistsByAuthorityEnumValidation implements ConstraintValidator<ExistsByAuthorityEnum, AuthorityEnum> {

    @Autowired
    private AuthorityService authorityService;

    @Override
    public boolean isValid(AuthorityEnum value, ConstraintValidatorContext context) {
        if (authorityService == null) {
            return true;
        }
        return !authorityService.existsByAuthorityEnum(value);
    }
}
