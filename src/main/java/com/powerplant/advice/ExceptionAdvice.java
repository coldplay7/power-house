package com.powerplant.advice;

import com.powerplant.dto.Error;
import com.powerplant.dto.Problem;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.ResponseEntity.badRequest;

@ControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @ExceptionHandler
    ResponseEntity<Problem> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return ResponseEntity.badRequest().body(Problem.builder()
                .httpStatusCode(BAD_REQUEST.value())
                .message(e.getMessage())
                .build());
    }

    @ExceptionHandler
    ResponseEntity<Problem> handleHttpMessageNotReadableException(MissingServletRequestParameterException e) {
        return ResponseEntity.badRequest().body(Problem.builder()
                .httpStatusCode(BAD_REQUEST.value())
                .message(e.getMessage())
                .build());
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    ResponseEntity<Problem> handleArgumentNotValidException(ConstraintViolationException e) {
        return badRequest().body(Problem.builder()
                .httpStatusCode(BAD_REQUEST.value())
                .errors(e.getConstraintViolations()
                        .stream()
                        .map(v -> Error.builder()
                                .field(v.getPropertyPath().toString())
                                .message(v.getMessage())
                                .build())
                        .collect(Collectors.toList())).build());
    }

    @ExceptionHandler
    ResponseEntity<Problem> handleException(Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return ResponseEntity.badRequest().body(Problem.builder()
                .httpStatusCode(INTERNAL_SERVER_ERROR.value())
                .message("There was problem in the system. Please contact administration.")
                .build());
    }

}
