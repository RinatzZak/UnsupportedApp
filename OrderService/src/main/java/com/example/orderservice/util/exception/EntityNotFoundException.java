package com.example.orderservice.util.exception;

import com.example.orderservice.util.ErrorType;

public class EntityNotFoundException extends BaseException {
    public EntityNotFoundException() {
        super("Entity not found", ErrorType.ENTITY_NOT_FOUND);
    }
}
