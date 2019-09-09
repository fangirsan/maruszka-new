package com.maruszka.constraints.integer;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class IntegerNotZeroValidator implements ConstraintValidator<IntegerNotZeroConstraint, Integer> {

    @Override
    public void initialize(IntegerNotZeroConstraint integer) {
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext cxt) {

        if (integer != null) {
            return true;
        } else {
            return false;
        }
    }

}
