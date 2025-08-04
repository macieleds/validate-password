package com.edisonmaciel.password.validator.service;

import com.edisonmaciel.password.validator.domain.password.request.PasswordRequest;
import com.edisonmaciel.password.validator.exceptions.PasswordException;
import com.edisonmaciel.password.validator.service.password.PasswordService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
public class PasswordServiceImpl implements PasswordService {

    private static final Map<String, String> RULES = Map.of(
            ".*[A-Z].*", "Password must contain at least one uppercase letter",
            ".*[a-z].*", "Password must contain at least one lowercase letter",
            ".*\\d.*", "Password must contain at least one number",
            ".*[!@#$%^&*(),.?\":{}|<>].*", "Password must contain at least one special character"
    );

    @Override
    public void validate(final PasswordRequest passwordRequest) {
        final String password = passwordRequest.password();

        if (isBlank(password)) {
            throw new PasswordException("Password cannot be empty");
        }

        if (password.length() < 8) {
            throw new PasswordException("Password must have at least 8 characters");
        }

        for (Map.Entry<String, String> rule : RULES.entrySet()) {
            if (!password.matches(rule.getKey())) {
                throw new PasswordException(rule.getValue());
            }
        }

        if (hasRepeatedCharacters(password)) {
            throw new PasswordException("Password must not contain repeated characters");
        }
    }

    private boolean hasRepeatedCharacters(final String password) {
        Set<Character> characters = new HashSet<>();
        for (char c : password.toCharArray()) {
            if (!characters.add(c)) {
                return true;
            }
        }
        return false;
    }
}