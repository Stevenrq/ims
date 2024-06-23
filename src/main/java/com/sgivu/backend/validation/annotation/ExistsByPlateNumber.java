package com.sgivu.backend.validation.annotation;

import com.sgivu.backend.validation.ExistsByPlateNumberValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ExistsByPlateNumberValidation.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsByPlateNumber {

    String message() default "ya se encuentra registrado";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
