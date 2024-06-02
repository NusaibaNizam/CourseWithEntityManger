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

	@Test
	@DirtiesContext
	void testFindById() {
		repository.save(new Course("English"));
		Course course = repository.findById(1l);
		assertEquals("English", course.getName());
	}

	@Test
	@DirtiesContext
	void testSaveInsert() {
		Course course = new Course("Bangla");
		repository.save(course);
		Course dbCourse = repository.findById(1l);
		assertEquals(course.getName(), dbCourse.getName());
	}

	@Test
	@DirtiesContext
	void testSaveUpdate() {
		repository.save(new Course("English"));
		Course dbCourse = repository.findById(1l);
		dbCourse.setName("Math");
		repository.save(dbCourse);
		assertEquals("Math", dbCourse.getName());
	}

	@Test
	@DirtiesContext
	void testDeleteById() {
		repository.save(new Course("English"));
		repository.deleteById(1l);
		Course course = repository.findById(1l);
		assertNull(course);
	}


}
