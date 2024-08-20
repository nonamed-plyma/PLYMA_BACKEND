package org.testboard.plyma_backend.global.error;

import jakarta.validation.ValidationException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.testboard.plyma_backend.global.error.exception.BaseException;

@Slf4j
@Getter
@RestControllerAdvice
public class PlymaExceptionHandler {
    @ExceptionHandler(BaseException.class)
    protected ResponseEntity<ErrorRespons> PlymahandlerException(BaseException exception){
        log.error("[KTHException] : " + exception.getErrorCode().getStatusCode());
        return new ResponseEntity<>(new ErrorRespons(exception.getErrorCode().getStatusCode(), exception.getErrorCode().getErrorMessage()), HttpStatusCode.valueOf(exception.getErrorCode().getStatusCode()));
    }

    @ExceptionHandler(NullPointerException.class)
    protected ResponseEntity<ErrorRespons> handNullPointException(NullPointerException exception){
        log.error("[NullPorinterException] : " + exception.getMessage(), exception);
        return new ResponseEntity<>(new ErrorRespons(400, "정보가 존재하지 않아 예외 발생함."), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<ErrorRespons> handleValidationException(ValidationException exception){
        log.error("[validationException] : " + exception.getMessage(), exception);
        return new ResponseEntity<>(new ErrorRespons(400, exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorRespons> handleException(Exception exception){
        log.error("[exception] : " + exception.getMessage(), exception);
        return new ResponseEntity<>(new ErrorRespons(500, exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
