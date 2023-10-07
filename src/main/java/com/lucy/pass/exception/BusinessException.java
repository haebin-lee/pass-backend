package com.lucy.pass.exception;

import lombok.Getter;
import lombok.Setter;

import static java.lang.String.format;
@Getter
@Setter
public class BusinessException extends RuntimeException implements ExceptionPolicy {

    protected final String code;
    protected final String message;

    public BusinessException(final PassException reason) {
        this.code = reason.getCode();
        this.message = reason.getMessage();
    }
    @Override
    public String getLocalizedMessage() {
        return getMessage();
    }

    public String toString() {
        return format("BusinessException(code=%s, message=%s)", this.getCode(), this.getMessage());
    }
}