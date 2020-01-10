package com.maruszka.constraints.bigDecimal;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BigDecimalNotNullValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface BigDecimalNotNullConstraint {

    String message() default "Value must not be zero";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}