package com.edisonmaciel.password.validator.domain.password.response;

public record PasswordResponse(String password) {

    public String getPassword() {
        return password;
    }

    public static PasswordResponse of(String password) {
        return new PasswordResponse(password);
    }
}