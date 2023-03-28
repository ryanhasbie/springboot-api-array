package com.api.springdemo.service;

import com.api.springdemo.model.Course;
import com.api.springdemo.model.request.CourseRequest;
import com.api.springdemo.util.specification.SearchCriteria;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ICourseService {
    List<Course> list();
    Course create(CourseRequest courseRequest);
    Course get(String id);
    void update(Course course, String id);
    void delete(String id);
    Page<Course> getByPagination(int page, int pageSize, String direction, String sortBy);
    List<Course> getByTitle(String value);
    List<Course> getByDesc(String value);
    List<Course> getBy(String keyword, String value);
    List<Course> listBy(SearchCriteria searchCriteria);
    public Resource download(String id);
}
