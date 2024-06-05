package com.example.jpa_hibernate_prac_project.repo;

import com.example.jpa_hibernate_prac_project.entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Objects;

import static com.example.jpa_hibernate_prac_project.constants.CourseConstants.FIND_COURSE_BY_NAME_NAMED_QUERY;
import static com.example.jpa_hibernate_prac_project.constants.CourseConstants.NAME_QUERY_PARAM;

@Repository
@Transactional
public class CourseRepository {
    @Autowired
    EntityManager em;

    public Course findById(Long id) {
        return em.find(Course.class, id);
    }

    public Course findByName(String name) {
        TypedQuery<Course> findCourseByName =
                em.createNamedQuery(FIND_COURSE_BY_NAME_NAMED_QUERY, Course.class);
        findCourseByName.setParameter(NAME_QUERY_PARAM, name);
        return findCourseByName.getSingleResult();
    }

    public Long save(Course course) {
        if (Objects.nonNull(course.getId())) {
            em.merge(course);
        } else {
            em.persist(course);
        }
        em.flush();

        em.close();
        return course.getId();
    }

    public void deleteById(Long id) {
        Course course = findById(id);
        if (Objects.nonNull(course)) {
            em.remove(course);
        }
    }

    @Modifying
    public int updateAllCourseCredit(Double credit){
        Query nativeQuery = em.createNativeQuery("UPDATE Course SET name = 'gh'", Course.class);
   //     nativeQuery.setParameter("credit", credit);
        int rows = nativeQuery.executeUpdate();
//        em.getTransaction().commit();
        return rows;
    }
}
