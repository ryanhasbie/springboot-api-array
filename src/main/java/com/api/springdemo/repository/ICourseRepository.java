package com.api.springdemo.repository;

import com.api.springdemo.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICourseRepository extends JpaRepository<Course, String> {
    @Query("SELECT c FROM Course c WHERE c.title LIKE %?1%")
    List<Course> findByTitle(String title);

    @Query("SELECT c FROM Course c WHERE c.description LIKE %?1%")
    List<Course> findByDescription(String description);

}
