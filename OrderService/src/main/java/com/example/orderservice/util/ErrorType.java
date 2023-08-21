package com.example.orderservice.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {
    ENTITY_NOT_FOUND(HttpStatus.UNPROCESSABLE_ENTITY),
    APP_ERROR(HttpStatus.INTERNAL_SERVER_ERROR),
    USER_EXISTS(HttpStatus.CONFLICT),
    PASSWORD_INCORRECT(HttpStatus.BAD_REQUEST),
    VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST);

    private final HttpStatus status;
}
