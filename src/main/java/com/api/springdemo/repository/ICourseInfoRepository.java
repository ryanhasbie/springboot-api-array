package com.api.springdemo.repository;

import com.api.springdemo.model.CourseInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseInfoRepository extends JpaRepository<CourseInfo, String> {
}
