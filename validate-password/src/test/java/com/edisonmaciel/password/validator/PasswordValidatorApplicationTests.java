package com.edisonmaciel.password.validator;

import com.edisonmaciel.password.validator.domain.password.request.PasswordRequest;
import com.edisonmaciel.password.validator.exceptions.PasswordException;
import com.edisonmaciel.password.validator.service.password.PasswordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class PasswordServiceTest {

    @Autowired
    private PasswordService passwordService;

    @Test
    void shouldRejectEmptyPassword() {
        assertThrows(PasswordException.class, () -> passwordService.validate(new PasswordRequest("")));
    }

    @Test
    void shouldRejectNullPassword() {
        assertThrows(PasswordException.class, () -> passwordService.validate(new PasswordRequest(null)));
    }

    @Test
    void shouldRejectPasswordShorterThan8Characters() {
        assertThrows(PasswordException.class, () -> passwordService.validate(new PasswordRequest("Ab1!e")));
    }

    @Test
    void shouldRejectPasswordWithoutUppercase() {
        assertThrows(PasswordException.class, () -> passwordService.validate(new PasswordRequest("abtp9!fok")));
    }

    @Test
    void shouldRejectPasswordWithoutLowercase() {
        assertThrows(PasswordException.class, () -> passwordService.validate(new PasswordRequest("ABTP9!FOK")));
    }

    @Test
    void shouldRejectPasswordWithoutNumber() {
        assertThrows(PasswordException.class, () -> passwordService.validate(new PasswordRequest("AbTp!fok")));
    }

    @Test
    void shouldRejectPasswordWithoutSpecialCharacter() {
        assertThrows(PasswordException.class, () -> passwordService.validate(new PasswordRequest("AbTp9fok")));
    }

    @Test
    void shouldRejectPasswordWithRepeatedCharacters1() {
        assertThrows(PasswordException.class, () -> passwordService.validate(new PasswordRequest("AbTp9!foo")));
    }

    @Test
    void shouldRejectPasswordWithRepeatedCharacters2() {
        assertThrows(PasswordException.class, () -> passwordService.validate(new PasswordRequest("AbTp9!foA")));
    }

    @Test
    void shouldRejectPasswordWithWhitespace() {
        assertThrows(PasswordException.class, () -> passwordService.validate(new PasswordRequest("AbTp9 fok")));
    }

    @Test
    void shouldAcceptValidPassword() {
        assertDoesNotThrow(() -> passwordService.validate(new PasswordRequest("AbTp9!fok")));
    }
}