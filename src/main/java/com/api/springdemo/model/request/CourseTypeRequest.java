package com.api.springdemo.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CourseTypeRequest {
    @NotBlank
    @Size(min = 5)
    private String courseTypeName;

    public String getCourseTypeName() {
        return courseTypeName;
    }

    public void setCourseTypeName(String courseTypeName) {
        this.courseTypeName = courseTypeName;
    }
}
