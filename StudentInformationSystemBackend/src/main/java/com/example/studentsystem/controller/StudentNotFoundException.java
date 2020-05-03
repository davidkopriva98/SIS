package com.example.studentsystem.controller;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(Integer id) {
        super("Could not find student with id " + id);
    }
}
