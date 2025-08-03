package com.edisonmaciel.password.validator.exceptions.handler;

import com.edisonmaciel.password.validator.controller.advice.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Void>> handleRuntime(RuntimeException ex, HttpServletRequest request) {
        if (ex instanceof ApiException apiEx) {
            return buildErrorResponse(apiEx.getStatus(), null, apiEx.getErrors(), request.getRequestURI());
        }
        return handleUnexpected(ex, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleUnexpected(Exception ex, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), null, request.getRequestURI());
    }

    private ResponseEntity<ApiResponse<Void>> buildErrorResponse(HttpStatus status, String message, List<FieldMessage> errors, String path) {
        ApiResponse<Void> response = new ApiResponse<>(
                status.value(),
                formatStatusName(status),
                message,
                now(),
                path,
                null,
                errors
        );
        return ResponseEntity.status(status).body(response);
    }

    private static String now() {
        return OffsetDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    private static String formatStatusName(HttpStatus status) {
        return Arrays.stream(status.name().toLowerCase().split("_"))
                .map(word -> Character.toUpperCase(word.charAt(0)) + word.substring(1))
                .collect(Collectors.joining(" "));
    }
}