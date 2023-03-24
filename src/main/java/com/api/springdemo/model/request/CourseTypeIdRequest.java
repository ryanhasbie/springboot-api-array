package com.api.springdemo.model.request;

import jakarta.validation.constraints.NotBlank;

public class CourseTypeIdRequest {
    private String courseTypeId;

    public String getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(String courseTypeId) {
        this.courseTypeId = courseTypeId;
    }
}
