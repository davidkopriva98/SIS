package com.example.studentsystem.controller;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(Integer id) {
        super("Could not find course with id " + id);
    }
}
