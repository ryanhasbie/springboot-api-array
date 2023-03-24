package com.api.springdemo.service;

import com.api.springdemo.model.Course;
import com.api.springdemo.util.CourseKey;

import java.util.List;
import java.util.Optional;

public interface ICourseServiceArray {
    List<Course> list();
    Course create(Course course);
    Optional<Course> get(String id);
    void update(Course course, String id);
    void delete(String id);
    List<Course> findBy(CourseKey by, String value) throws Exception;
}
