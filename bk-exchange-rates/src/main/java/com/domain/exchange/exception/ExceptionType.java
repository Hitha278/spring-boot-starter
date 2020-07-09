package com.domain.exchange.exception;

public enum ExceptionType {
    VALIDATION_EXCEPTION("validation.exception");

    String value;

    ExceptionType(String value) {
        this.value = value;
    }

    String getValue() {
        return this.value;
    }
}
