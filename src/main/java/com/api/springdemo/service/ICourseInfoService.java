package com.api.springdemo.service;

import com.api.springdemo.model.CourseInfo;
import org.springframework.data.domain.Page;

public interface ICourseInfoService {
    CourseInfo create(CourseInfo courseInfo);
    CourseInfo getById(String id);
    Page<CourseInfo> getAll(int page, int pageSize, String direction, String sortBy);
}
