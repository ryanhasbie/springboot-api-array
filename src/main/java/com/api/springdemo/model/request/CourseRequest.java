package com.api.springdemo.model.request;

import com.api.springdemo.model.CourseInfo;
import com.api.springdemo.model.CourseType;
import jakarta.validation.constraints.NotBlank;

public class CourseRequest {
    @NotBlank(message = "{invalid.title.required}")
    private String title;

    @NotBlank(message = "{invalid.description.required}")
    private String description;

    @NotBlank(message = "{invalid.link.required}")
    private String link;

    private CourseInfoRequest courseInfo;

    private CourseTypeIdRequest courseType;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public CourseInfoRequest getCourseInfo() {
        return courseInfo;
    }

    public void setCourseInfo(CourseInfoRequest courseInfo) {
        this.courseInfo = courseInfo;
    }

    public CourseTypeIdRequest getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseTypeIdRequest courseType) {
        this.courseType = courseType;
    }
}
