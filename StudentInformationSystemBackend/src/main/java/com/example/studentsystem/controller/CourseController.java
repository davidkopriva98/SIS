package com.example.studentsystem.controller;

import com.example.studentsystem.db.CourseRepository;
import com.example.studentsystem.model.Course;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//@CrossOrigin(origins = "http://example.com", maxAge = 3600)
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", exposedHeaders = "Authorization")
@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseRepository repository;

    public CourseController(CourseRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/all")
    List<Course> all() {
        return repository.findAll();
    }

    @GetMapping("/name/{name}")
    List<Course> allWithName(@PathVariable String name) {
        List<Course> all = repository.findAll();
        List<Course> result = new ArrayList<>();

        for (Course course : all) {
            if (course.getName().toLowerCase().contains(name.toLowerCase()))
                result.add(course);
        }
        return result;
    }

    @GetMapping("/{id}")
    Course one(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
    }

    @PostMapping
    Course addCourse(@RequestBody Course newCourse) {

        if (repository.findByName(newCourse.getName().toLowerCase()).size() != 0)
            throw new CourseFoundException(newCourse.getName());

        return repository.save(newCourse);
    }

    @DeleteMapping("/{id}")
    void deleteCourse(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}
