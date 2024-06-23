package com.sgivu.backend.validation.annotation;

import com.sgivu.backend.validation.ExistsByPhoneValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ExistsByPhoneValidation.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsByPhone {

    String message() default "ya se encuentra registrado";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
