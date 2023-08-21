package com.example.orderservice.util.exception;

import com.example.orderservice.util.ErrorType;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    private final ErrorType errorType;

    /**
     * Class constructor specifying messages for exception
     */
    public BaseException(String message) {
        super(message);
        this.errorType = ErrorType.APP_ERROR;
    }

    /**
     * Class constructor specifying message and {@link ErrorType} for exception
     */
    public BaseException(String message, ErrorType errorType) {
        super(message);
        this.errorType = errorType;
    }
}
