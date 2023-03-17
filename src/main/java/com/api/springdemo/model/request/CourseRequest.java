package com.api.springdemo.model.request;

import jakarta.validation.constraints.NotBlank;

public class CourseRequest {
    @NotBlank(message = "{invalid.title.required}")
    private String title;

    @NotBlank(message = "{invalid.description.required}")
    private String description;

    @NotBlank(message = "{invalid.link.required}")
    private String link;

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
}
