package dev.unit.obab.core.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseType {

    // COMMON
    SUCCESS("CM00", "success :)"),
    FAILURE("CM99", "failure :("), // Internal Server Error

    ARGUMENT_NOT_VALID("CM01", "Argument 유효성 검증에 실패하였습니다."),
    REQUEST_NOT_VALID("CM02", "유효하지 않는 요청입니다."),
    REQUEST_UNAUTHORIZED("CM03", "비인증된 요청입니다.");


    private final String code;
    private final String message;
}
