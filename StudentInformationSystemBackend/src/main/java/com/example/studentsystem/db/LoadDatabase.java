package com.example.studentsystem.db;

import com.example.studentsystem.model.Course;
import com.example.studentsystem.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Slf4j
public class LoadDatabase {

    @Bean
    CommandLineRunner initDB(CourseRepository courseRepository, StudentRepository studentRepository) {
        return args -> {
            String desc = "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Inventore sed consequuntur error " +
                    "repudiandae numquam deserunt quisquam repellat libero asperiores earum nam nobis, culpa ratione " +
                    "quam perferendis esse, cupiditate neque quas!";
            Course prog1 = new Course("Programming 1", desc);
            Course com = new Course("Computer Communications", desc);
            Course math = new Course("Mathematics", desc);
            log.info("Preloading classes " + courseRepository.save(prog1));
            log.info("Preloading classes " + courseRepository.save(new Course("Operating Systems", desc)));
            log.info("Preloading classes " + courseRepository.save(com));
            log.info("Preloading classes " + courseRepository.save(new Course("Databases", desc)));
            log.info("Preloading classes " + courseRepository.save(new Course("Programming 2", desc)));
            log.info("Preloading classes " + courseRepository.save(new Course("Discrete Structures", desc)));
            log.info("Preloading classes " + courseRepository.save(math));
            log.info("Preloading classes " + courseRepository.save(new Course("Introduction to Computer Science", desc)));
            log.info("Preloading classes " + courseRepository.save(new Course("Computer Architecture", desc)));
            log.info("Preloading classes " + courseRepository.save(new Course("Algorithms and Data Structures 1", desc)));
            log.info("Preloading classes " + courseRepository.save(new Course("Algorithms and Data Structures 2", desc)));
            log.info("Preloading classes " + courseRepository.save(new Course("Project Practicum", desc)));
            log.info("Preloading classes " + courseRepository.save(new Course("Industrial Practice", desc)));
            log.info("Preloading classes " + courseRepository.save(new Course("Database 2", desc)));
            log.info("Preloading classes " + courseRepository.save(new Course("Information Systems", desc)));
            log.info("Preloading classes " + courseRepository.save(new Course("Graphic Design", desc)));
            log.info("Preloading classes " + courseRepository.save(new Course("Computer Organization", desc)));

            log.info("Preloading students " + studentRepository.save(new Student(63190001, "Janez", "Novak", List.of(prog1, com, math))));
            log.info("Preloading students " + studentRepository.save(new Student(63190002, "Martin", "Horvat")));
            log.info("Preloading students " + studentRepository.save(new Student(63190004, "Ana", "Kranjc")));
            log.info("Preloading students " + studentRepository.save(new Student(63190004, "Marija", "Golob")));
        };
    }

    @Bean
    CommandLineRunner initStudentDB(StudentRepository studentRepository) {
        return args -> {

        };
    }
}
