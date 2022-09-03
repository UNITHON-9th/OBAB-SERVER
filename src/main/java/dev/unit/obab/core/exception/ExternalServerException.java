package dev.unit.obab.core.exception;

import dev.unit.obab.core.domain.ResponseType;

public class ExternalServerException extends BaseException {

    public ExternalServerException(ResponseType responseType) {
        super(responseType);
    }
}
