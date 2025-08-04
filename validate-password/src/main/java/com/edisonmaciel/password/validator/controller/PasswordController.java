package com.edisonmaciel.password.validator.controller;

import com.edisonmaciel.password.validator.domain.password.request.PasswordRequest;
import com.edisonmaciel.password.validator.service.password.PasswordService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class PasswordController {

    private final PasswordService passwordService;

    public PasswordController(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @PostMapping("/password")
    public void validatePassword(@RequestBody final PasswordRequest password) {
        passwordService.validate(password);
    }
}