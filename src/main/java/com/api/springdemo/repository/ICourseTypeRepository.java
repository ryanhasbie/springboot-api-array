package com.api.springdemo.repository;

import com.api.springdemo.model.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseTypeRepository extends JpaRepository<CourseType, String> {

}
