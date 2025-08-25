package com.maharjan.exceptionHandling.globalExceptions;

import com.maharjan.exceptionHandling.customExceptions.EmptyInputException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class MyControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EmptyInputException.class)
    public ResponseEntity<String> handleEmptyInputException(EmptyInputException e) {
        return new ResponseEntity<>("Input Fields are Empty. Please look into it.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
        return new ResponseEntity<>("No value is present in DB. Please change your request.", HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//        return super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
        return new ResponseEntity<Object>("Please change your HTTP method type.", status);
    }
}
