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
	@DirtiesContext
	void setUp(){
		repository.save(new Course("English"));
	}

	@Test
	void testFindById() {
		Course course = repository.findById(1l);
		assertEquals(course.getName(), "English");
	}


	@Test
	@DirtiesContext
	void testDeleteById() {
		repository.deleteById(1l);
		Course course = repository.findById(1l);
		assertNull(course);
	}

	@AfterEach
	@DirtiesContext
	void cleanUp(){
		repository.deleteById(1l);
	}

}
