package dev.unit.obab.core.exception;

import dev.unit.obab.core.domain.ResponseType;

public class BadRequestException extends BaseException {

    public BadRequestException(ResponseType responseType) {
        super(responseType);
    }
}
