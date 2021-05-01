package com.example.poc.multiversion.v2.common;

import lombok.experimental.SuperBuilder;

import javax.validation.*;
import java.util.Set;

@SuperBuilder
public abstract class SelfValidating<T> {

    private Validator validator;

    public SelfValidating() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    /**
     * Evaluates all Bean Validations on the attributes of this
     * instance.
     */
    public void validateSelf() {
        Set<ConstraintViolation<T>> violations = validator.validate((T) this);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}