package dev.unit.obab.core.exception;

import dev.unit.obab.core.domain.ResponseType;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    private final ResponseType responseType;

    public BaseException(ResponseType responseType) {
        this.responseType = responseType;
    }
}
