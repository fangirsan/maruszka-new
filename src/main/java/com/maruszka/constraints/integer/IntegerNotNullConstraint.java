package com.maruszka.constraints.integer;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IntegerNotNullValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface IntegerNotNullConstraint {

    String message() default "Value must not be zero";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}