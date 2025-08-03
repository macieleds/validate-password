package com.edisonmaciel.password.validator.validation;

import com.edisonmaciel.password.validator.validation.utils.PasswordValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordCheckValidation implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(ValidPassword annotation) {
    }

    @Override
    public boolean isValid(final String password, final ConstraintValidatorContext context) {
        if (password == null) return false;

        boolean isValid = PasswordValidation.isValidPassword(password);
        boolean hasRepeated = PasswordValidation.hasRepeatedCharacters(password);

        if (!isValid || hasRepeated) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Password does not match the standards")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
