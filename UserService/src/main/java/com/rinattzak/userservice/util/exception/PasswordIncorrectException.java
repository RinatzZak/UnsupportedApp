package com.rinattzak.userservice.util.exception;

import com.rinattzak.userservice.util.ErrorType;

public class PasswordIncorrectException extends BaseException {
    /**
     * Class constructor specifying messages and {@link ErrorType} for exception
     */
    public PasswordIncorrectException() {
        super("Incorrect password", ErrorType.PASSWORD_INCORRECT);
    }

}
