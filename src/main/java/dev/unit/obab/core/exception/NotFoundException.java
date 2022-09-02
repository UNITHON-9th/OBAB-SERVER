package dev.unit.obab.core.exception;

import dev.unit.obab.core.domain.ResponseType;

public class NotFoundException extends BaseException {

    public NotFoundException(ResponseType responseType) {
        super(responseType);
    }

}
