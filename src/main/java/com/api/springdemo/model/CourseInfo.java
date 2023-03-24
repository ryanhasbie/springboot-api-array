package com.api.springdemo.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_course_info")
public class CourseInfo {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String courseInfoId;
    @Column(name = "duration", nullable = false)
    private String duration;
    @Column(name = "level", nullable = false)
    private String level;

    public String getCourseInfoId() {
        return courseInfoId;
    }

    public void setCourseInfoId(String courseInfoId) {
        this.courseInfoId = courseInfoId;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
