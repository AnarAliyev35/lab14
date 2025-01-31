package com.example.secureblog.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface StrongPassword {
    String message() default "Password must be at least 14 characters long and contain uppercase, lowercase, number and special character";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
