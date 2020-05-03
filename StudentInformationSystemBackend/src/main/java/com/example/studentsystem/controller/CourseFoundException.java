package com.example.studentsystem.controller;

public class CourseFoundException extends RuntimeException {
    public CourseFoundException(String name) {
        super(String.format("Could not insert new course with name %s, because course with same name already exists", name));
    }
}
