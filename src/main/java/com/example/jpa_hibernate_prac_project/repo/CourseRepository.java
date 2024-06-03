package com.example.jpa_hibernate_prac_project.repo;

import com.example.jpa_hibernate_prac_project.entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
@Transactional
public class CourseRepository {
    @Autowired
    EntityManager em;

    public Course findById(Long id) {
        return em.find(Course.class, id);
    }


//    public Course findByName(String name) {
//        return em.createNamedQuery();
//    }

    public Long save(Course course) {
        if (Objects.nonNull(course.getId())) {
            em.merge(course);
        } else {
            em.persist(course);
        }
        em.flush();
        return course.getId();
    }

    public void deleteById(Long id) {
        Course course = findById(id);
        if (Objects.nonNull(course)) {
            em.remove(course);
        }
    }
}
