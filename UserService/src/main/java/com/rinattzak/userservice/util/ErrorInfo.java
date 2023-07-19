package com.rinattzak.userservice.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorInfo {
    private final String uri;
    private final ErrorType type;
    private final String message;
    private final LocalDateTime timeStamp;

}
