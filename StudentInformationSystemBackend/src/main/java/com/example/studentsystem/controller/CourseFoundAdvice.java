package com.example.studentsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CourseFoundAdvice {
    @ResponseBody
    @ExceptionHandler(CourseFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String courseFoundHandler(CourseFoundException cfe) {
        return cfe.getLocalizedMessage();
    }
}
