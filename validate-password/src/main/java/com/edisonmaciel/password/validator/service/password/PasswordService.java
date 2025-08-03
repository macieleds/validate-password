package com.edisonmaciel.password.validator.service.password;

import com.edisonmaciel.password.validator.domain.password.request.PasswordRequest;

public interface PasswordService {
    void validate(final PasswordRequest password);
}