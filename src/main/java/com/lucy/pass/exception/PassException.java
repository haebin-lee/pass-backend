package com.lucy.pass.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PassException implements ExceptionPolicy {
    NOT_FOUND_MEETING("There is no meeting with id");

    private final String code = this.name();
    private final String message;
}