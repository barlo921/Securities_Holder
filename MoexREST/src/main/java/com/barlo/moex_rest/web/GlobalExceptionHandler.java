package com.barlo.moex_rest.web;

import com.barlo.moex_rest.exception.AbstractMoexRESTException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AbstractMoexRESTException.class)
    public ResponseEntity<Object> handleException(Exception e) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400
        log.info(status + ": " + e.getMessage());
        return new ResponseEntity<>(e.getMessage(), status);
    }

}
