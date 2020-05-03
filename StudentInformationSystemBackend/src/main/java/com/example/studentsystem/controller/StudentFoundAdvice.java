package com.example.studentsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class StudentFoundAdvice {
    @ResponseBody
    @ExceptionHandler(StudentFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String studentFoundHandler(StudentFoundException sfe) {
        return sfe.getLocalizedMessage();
    }
}
