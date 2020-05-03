package com.example.studentsystem.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@Entity
public class Student {
    private @Id Integer id;
    private String firstName;
    private String lastName;
    @OneToMany(targetEntity = Course.class, cascade = CascadeType.ALL)
    private List<Course> enrolledCourses;

    public Student() {
    }

    public Student(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.enrolledCourses = new ArrayList<>();
    }

    public Student(int id, String firstName, String lastName, List<Course> enrolled) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.enrolledCourses = enrolled;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(List<Course> enrolledCourses) {
        this.enrolledCourses.addAll(enrolledCourses);
    }

    public void setNewEnrolledCourse(Course enrolledCourse) { this.enrolledCourses.add(enrolledCourse); }

    public void setRemoveEnrolledCourse(Course enrolledCourse) { this.enrolledCourses.remove(enrolledCourse); }

    public void setRemoveEnrolledCourses(List<Course> enrolledCourse) { this.enrolledCourses.removeAll(enrolledCourse); }
}
