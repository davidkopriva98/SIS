package com.example.studentsystem.controller;

public class StudentFoundException extends RuntimeException {
    public StudentFoundException(Integer id) {
        super(String.format("Could not insert new student with id %d, because student already exists.", id));
    }
}
