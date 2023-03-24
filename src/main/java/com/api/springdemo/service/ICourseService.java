package com.api.springdemo.service;

import com.api.springdemo.model.Course;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICourseService {
    List<Course> list();
    Course create(Course course);
    Course get(String id);
    void update(Course course, String id);
    void delete(String id);
    Page<Course> getByPagination(int page, int pageSize, String direction, String sortBy);
    List<Course> getByTitle(String value);
    List<Course> getByDesc(String value);
    List<Course> getBy(String keyword, String value);
}
