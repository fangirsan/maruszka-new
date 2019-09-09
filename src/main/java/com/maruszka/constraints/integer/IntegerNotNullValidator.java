package com.maruszka.constraints.integer;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IntegerNotNullValidator implements ConstraintValidator<IntegerNotNullConstraint, Integer> {

    @Override
    public void initialize(IntegerNotNullConstraint integer) {
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
