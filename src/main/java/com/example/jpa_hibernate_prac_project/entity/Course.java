package com.example.jpa_hibernate_prac_project.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

import static com.example.jpa_hibernate_prac_project.constants.CourseConstants.FIND_COURSE_BY_NAME_NAMED_QUERY;
import static com.example.jpa_hibernate_prac_project.constants.CourseConstants.NAME_QUERY_PARAM;

@Entity
@Table(name = "Course")
@NamedQueries(value = {
        @NamedQuery(query = "Select c from Course c where c.name = :" + NAME_QUERY_PARAM,
                name = FIND_COURSE_BY_NAME_NAMED_QUERY)
})
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "credit")
    private Double credit;

    @CreationTimestamp
    @Column(name = "createDate", updatable = false, insertable = false)
    private LocalDateTime createDate;

    @UpdateTimestamp
    @Column(name = "updateDate", updatable = false, insertable = false)
    private LocalDateTime updateDate;

    protected Course() {
    }

    public Course(String name) {
        this.name = name;
    }

    public Course(String name, Double credit) {
        this.name = name;
        this.credit = credit;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }
}
