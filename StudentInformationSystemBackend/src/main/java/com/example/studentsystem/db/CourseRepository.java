package com.example.studentsystem.db;

import com.example.studentsystem.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findByName(String name);
    //custom methods
}
