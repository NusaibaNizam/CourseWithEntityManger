package com.example.jpa_hibernate_prac_project.repo;

import com.example.jpa_hibernate_prac_project.entity.Course;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class CourseRepositoryTests {

    @Autowired
    CourseRepository repository;

    @BeforeEach
    void setUp() {
        repository.save(new Course("English"));
    }

    @Test
    @DirtiesContext
    void testFindById() {
        Course course = repository.findById(1L);
        assertEquals("English", course.getName());
    }

    @Test
    @DirtiesContext
    void testSaveInsert() {
        Course course = new Course("Bangla");
        repository.save(course);
        Course dbCourse = repository.findById(2L);
        assertEquals(course.getName(), dbCourse.getName());
    }

    @Test
    @DirtiesContext
    void testSaveUpdate() {

        Course dbCourse = repository.findById(1L);
        dbCourse.setName("Math");
        repository.save(dbCourse);
        assertEquals("Math", dbCourse.getName());
    }

    @Test
    @DirtiesContext
    void testDeleteById() {
        repository.deleteById(1L);
        Course course = repository.findById(1L);
        assertNull(course);
    }

    @AfterEach
    void cleanUp() {
        repository.deleteById(1L);
    }

}
