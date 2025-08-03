package com.edisonmaciel.password.validator.exceptions;

import com.edisonmaciel.password.validator.exceptions.handler.ApiException;
import com.edisonmaciel.password.validator.exceptions.handler.FieldMessage;
import org.springframework.http.HttpStatus;

import java.util.List;

public class PasswordException extends RuntimeException implements ApiException {

    private final List<FieldMessage> errors;

    public PasswordException(String message) {
        super(message);
        this.errors = List.of(new FieldMessage("password", message));
    }

    public PasswordException(List<FieldMessage> errors) {
        super("Validation error");
        this.errors = errors;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.UNPROCESSABLE_ENTITY;
    }

    @Override
    public List<FieldMessage> getErrors() {
        return errors;
    }
}

