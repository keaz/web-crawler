package com.kasun.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = URLValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface URLConstraint {

    String message() default "The input list should contains valid URLs.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
