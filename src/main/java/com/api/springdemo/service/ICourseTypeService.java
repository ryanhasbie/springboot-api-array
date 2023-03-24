package com.api.springdemo.service;

import com.api.springdemo.model.Course;
import com.api.springdemo.model.CourseType;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICourseTypeService {
    CourseType create(CourseType courseType);
    CourseType getById(String id);
    Page<CourseType> getAll(int page, int pageSize, String direction, String sortBy);
    List<Course> getCourseById(String id);
    List<CourseType> findCourseType(String name);
}
