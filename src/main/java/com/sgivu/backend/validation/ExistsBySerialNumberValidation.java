package com.sgivu.backend.validation;

import com.sgivu.backend.service.VehicleService;
import com.sgivu.backend.validation.annotation.ExistsBySerialNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ExistsBySerialNumberValidation implements ConstraintValidator<ExistsBySerialNumber, String> {

    @Autowired
    private VehicleService vehicleService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (vehicleService == null) {
            return true;
        }
        return !vehicleService.existsBySerialNumber(value);
    }
}
