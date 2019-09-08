package com.maruszka.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class BigDecimalNotNullValidator implements ConstraintValidator<BigDecimalNotNullConstraint, BigDecimal> {

    @Override
    public void initialize(BigDecimalNotNullConstraint bigDecimal) {
    }

    @Override
    public boolean isValid(BigDecimal bigDecimal, ConstraintValidatorContext cxt) {

        if (bigDecimal != null) {
            return true;
        } else {
            return false;
        }
    }

}
