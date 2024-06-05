package com.example.jpa_hibernate_prac_project.repo;

import com.example.jpa_hibernate_prac_project.entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CourseRepositoryTests {

    private long id;
    @Autowired
    CourseRepository repository;
    @Autowired
    EntityManager entityManager;
    private Course english;

    @BeforeEach
    void setUp() {
        english = new Course("English", 8.9);
        entityManager.persist(english);
        entityManager.flush();
        id = english.getId();
    }

    @Test
    @DirtiesContext
    void testFindById() {
        Course course = repository.findById(id);
        assertEquals("English", course.getName());
    }

    @Test
    @DirtiesContext
    void testFindByName() {
        Course course = repository.findByName("English");
        assertEquals(id, course.getId());
    }

    @Test
    @DirtiesContext
    void testSaveInsert() {
        Course course = new Course("Bangla");
        Long id = repository.save(course);
        assertNotNull(id);
        Course dbCourse = repository.findById(id);
        assertEquals(course.getName(), dbCourse.getName());
    }

    @Test
    @DirtiesContext
    void testSaveUpdate() {
        Course dbCourse = repository.findById(id);
        dbCourse.setName("Math");
        dbCourse.setCredit(1.8);
        repository.save(dbCourse);
        assertEquals("Math", dbCourse.getName());
        assertEquals(1.8, dbCourse.getCredit());
    }

    @Test
    @DirtiesContext
    void testDeleteById() {
        repository.deleteById(id);
        Course course = repository.findById(id);
        assertNull(course);
    }

    @Test
    @DirtiesContext
    void testUpdateAllCourseCredit() {
        Course course = repository.findById(id);
        assertEquals(8.9, course.getCredit());
        repository.updateAllCourseCredit(1.5);
        course = repository.findById(id);
        assertEquals(1.5, course.getCredit());
    }
    @AfterEach
    void cleanUp() {
        entityManager.remove(english);
    }

}
