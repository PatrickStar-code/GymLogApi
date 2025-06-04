package com.Gymlog.Exceptions;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EmailExistException.class)
    public ResponseEntity<String> handleEmailExistException(EmailExistException e) {
        return ResponseEntity.status(HttpStatusCode.valueOf(409)).body(e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public  ResponseEntity<String> handleNotFoundException(NotFoundException e) {
        return  ResponseEntity.status(HttpStatusCode.valueOf(404)).body(e.getMessage());
    }

    @ExceptionHandler(PasswordConfirmIncorrectException.class)
    public  ResponseEntity<String> handlePasswordConfirmIncorrectException(PasswordConfirmIncorrectException e) {
        return  ResponseEntity.status(HttpStatusCode.valueOf(400)).body(e.getMessage());
    }

}
