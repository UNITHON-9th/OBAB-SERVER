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
    REQUEST_UNAUTHORIZED("CM03", "비인증된 요청입니다."),
    PARSE_FAILURE("CM04", "데이터를 파싱할 수 없습니다."),

    FCM_CONNECT_FAILURE("NO01", "FCM 서버에 연결할 수 없습니다."),
    FCM_KEY_NOT_FOUND("NO02", "Firebase 키파일을 찾을 수 없습니다."),

    USER_DUPLICATE_SUCCESS("US01", "이미 참여한 사용자입니다.");

    private final String code;
    private final String message;
}
