package com.sgivu.backend.validation;

import com.sgivu.backend.model.RoleEnum;
import com.sgivu.backend.service.RoleService;
import com.sgivu.backend.validation.annotation.ExistsByRoleEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ExistsByRoleEnumValidation implements ConstraintValidator<ExistsByRoleEnum, RoleEnum> {

    @Autowired
    private RoleService roleService;

    @Override
    public boolean isValid(RoleEnum value, ConstraintValidatorContext context) {
        if (roleService == null) {
            return true;
        }
        return !roleService.existsByRoleEnum(value);
    }
}
