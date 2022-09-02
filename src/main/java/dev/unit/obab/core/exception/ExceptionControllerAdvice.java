package dev.unit.obab.core.exception;

import dev.unit.obab.core.domain.ResponseEntity;
import dev.unit.obab.core.domain.ResponseType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BaseException.class)
    public <T> ResponseEntity<T> handleBusinessException(BaseException exception) {
        printLog(exception);
        return ResponseEntity.failureResponse(exception.getResponseType());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public <T> ResponseEntity<T> handleBadRequestException(BadRequestException exception) {
        printLog(exception);
        return ResponseEntity.failureResponse(exception.getResponseType());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public <T> ResponseEntity<T> handleNotFoundException(NotFoundException exception) {
        printLog(exception);
        return ResponseEntity.failureResponse(exception.getResponseType());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public <T> ResponseEntity<T> handleException(Exception exception) {
        printLog(exception.getClass().getName(), exception.getMessage());
        exception.printStackTrace();
        return ResponseEntity.failureResponse(ResponseType.FAILURE);
    }

    private void printLog(BaseException exception) {
        log.error(String.format("[Error] %s: %s", exception.getClass().getName(),
            exception.getResponseType().getMessage()));
    }

    private void printLog(String exceptionName, String message) {
        log.error(String.format("[Error] %s: %s", exceptionName, message));
    }

}
