package com.api.springdemo.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CourseInfoRequest {
    @NotBlank
    @Size(min = 5)
    private String duration;
    @NotBlank
    @Size(min = 5)
    private String level;

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
