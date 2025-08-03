package com.edisonmaciel.password.validator.exceptions.handler;

import org.springframework.http.HttpStatus;

import java.util.List;

public interface ApiException {
    HttpStatus getStatus();
    List<FieldMessage> getErrors();
    String getMessage();
}

