package com.bridgelabz.iplbettingnotesspring.exception;

import com.bridgelabz.iplbettingnotesspring.util.ResponseClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BetExceptionHandler {
    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<ResponseClass> handleHiringException(UserNotFound userNotFound){
        ResponseClass responseClass = new ResponseClass();
        responseClass.setErrorCode(400);
        responseClass.setMessage(userNotFound.getMessage());
        return new ResponseEntity<>(responseClass, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
