package com.example.studentsystem.controller;

public class StudentAlreadyEnrolledException extends RuntimeException {
    public StudentAlreadyEnrolledException(Integer id) {
        super("Student already enrolled to course with id " + id);
    }
}
