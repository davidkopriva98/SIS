package com.example.studentsystem.controller;

import com.example.studentsystem.db.CourseRepository;
import com.example.studentsystem.db.StudentRepository;
import com.example.studentsystem.model.Course;
import com.example.studentsystem.model.Student;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", exposedHeaders = "Authorization")
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentController(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping("/all")
    List<Student> all() {
        return studentRepository.findAll();
    }


    @GetMapping("/{id}")
    Student one(@PathVariable Integer id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    @GetMapping("/name/{name}")
    List<Student> findStudentsByName(@PathVariable String name) {
        List<Student> result = new ArrayList<>();
        List<Student> all = studentRepository.findAll();

        for (Student student : all) {
            if (student.getFirstName().toLowerCase().contains(name.toLowerCase()) ||
            student.getLastName().toLowerCase().contains(name.toLowerCase()))
                result.add(student);
        }
        return result;
    }

    @PostMapping
    Student addStudent(@RequestBody Student newStudent) {
        if (studentRepository.findById(newStudent.getId()).isPresent())
            throw new StudentFoundException(newStudent.getId());

        return studentRepository.save(newStudent);
    }


    @GetMapping("/{id}/courses")
    List<Course> getEnrolledCourses(@PathVariable Integer id) {
        Student student = one(id);
        return student.getEnrolledCourses();
    }

    @PostMapping("/{idS}/enroll/{idC}")
    Student addCourseToStudent(@PathVariable Integer idS, @PathVariable Integer idC) {
        Student student = one(idS);
        Course course = courseRepository.findById(idC)
                    .orElseThrow(() -> new CourseNotFoundException(idC));
        student.setNewEnrolledCourse(course);
        return studentRepository.save(student);
    }

    @PostMapping("/{idS}/enrollMultiple/{courses}")
    Student addCoursesToStudent(@PathVariable Integer idS, @PathVariable String courses) {
        Student student = one(idS);
        String [] coursesId = courses.split("&");
        List<Course> courseList = new ArrayList<>();
        for (String id : coursesId) {
            if (id.matches("\\d+")) {
                Integer idC = Integer.parseInt(id);
                Course course = courseRepository.findById(idC)
                        .orElseThrow(() -> new CourseNotFoundException(idC));
                courseList.add(course);
            }
        }
        student.setEnrolledCourses(courseList);
        return studentRepository.save(student);
    }

    @PostMapping("/{idS}/disenroll/{idC}")
    Student removeCourseFromStudent(@PathVariable Integer idS, @PathVariable Integer idC) {
        Student student = one(idS);
        Course course = courseRepository.findById(idC)
                    .orElseThrow(() -> new CourseNotFoundException(idC));
        student.setRemoveEnrolledCourse(course);
        return studentRepository.save(student);
    }

    @PostMapping("/{idS}/disenrollMultiple/{courses}")
    Student removeCourseFromStudent(@PathVariable Integer idS, @PathVariable String courses) {
        Student student = one(idS);
        String [] coursesId = courses.split("&");
        List<Course> courseList = new ArrayList<>();
        for (String id : coursesId) {
            if (id.matches("\\d+")) {
                Integer idC = Integer.parseInt(id);
                Course course = courseRepository.findById(idC)
                        .orElseThrow(() -> new CourseNotFoundException(idC));
                courseList.add(course);
            }
        }
        student.setRemoveEnrolledCourses(courseList);
        return studentRepository.save(student);
    }

    @DeleteMapping("/{id}")
    void deleteStudent(@PathVariable Integer id) {
        studentRepository.deleteById(id);
    }
}
