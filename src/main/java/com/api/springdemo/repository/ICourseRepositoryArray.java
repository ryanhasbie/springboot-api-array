package com.api.springdemo.repository;

import com.api.springdemo.model.Course;
import com.api.springdemo.util.CourseKey;

import java.util.List;
import java.util.Optional;

public interface ICourseRepositoryArray {
    List<Course> getAll() throws Exception;
    Course create(Course course) throws Exception;
    Optional<Course> findById(String id) throws Exception;
    void update(Course course, String id) throws Exception;
    void delete(String id) throws Exception;
    void addBucket(String by, String value, List<Course> bucket, Course course) throws Exception;
    Optional<List<Course>> findBy(CourseKey by, String value) throws Exception;
}
