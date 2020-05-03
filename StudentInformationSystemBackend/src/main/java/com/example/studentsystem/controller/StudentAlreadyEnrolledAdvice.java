package com.example.studentsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class StudentAlreadyEnrolledAdvice {
    @ResponseBody
    @ExceptionHandler(StudentAlreadyEnrolledException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String studentAlreadyEnrolledHandler(StudentAlreadyEnrolledException snfe) {
        return snfe.getLocalizedMessage();
    }
}
