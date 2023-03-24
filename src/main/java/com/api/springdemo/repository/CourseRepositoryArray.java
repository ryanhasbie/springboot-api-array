package com.api.springdemo.repository;

import com.api.springdemo.model.Course;
import com.api.springdemo.util.CourseKey;
import com.api.springdemo.util.IRandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public class CourseRepositoryArray implements ICourseRepositoryArray {
    @Autowired
    IRandomStringGenerator iRandomStringGenerator;

    private List<Course> courses = new ArrayList<>();

    @Override
    public List<Course> getAll() throws Exception {
        return courses;
    }

    @Override
    public Course create(Course course) throws Exception {
        course.setCourseId(iRandomStringGenerator.random());
        courses.add(course);
        return course;
    }

    @Override
    public Optional<Course> findById(String id) throws Exception {
        for (Course course: courses) {
            if (course.getCourseId().equals(id)) {
                return Optional.of(course);
            }
        }
        return Optional.empty();
    }

    @Override
    public void update(Course course, String id) throws Exception {
        for (Course c : courses) {
            if (c.getCourseId().equals(id)) {
                c.setTitle(course.getTitle());
                c.setDescription(course.getDescription());
                c.setLink(course.getLink());
                break;
            }
        }
    }

    @Override
    public void delete(String id) throws Exception {
        for (Course c : courses) {
            if (c.getCourseId().equals(id)) {
                courses.remove(c);
                break;
            }
        }
    }

    @Override
    public void addBucket(String by, String value, List<Course> bucket, Course course) {
        if (by.toLowerCase().contains(value)) {
            bucket.add(course);
        }
    }

    @Override
    public Optional<List<Course>> findBy(CourseKey by, String value) throws Exception {
        List<Course> results = new ArrayList<>();
        for (Course course: courses) {
            switch (by) {
                case title:
                    if (course.getTitle().toLowerCase().contains(value)) {
                        results.add(course);
                    }
                case description:
                    if (course.getDescription().toLowerCase().contains(value)) {
                        results.add(course);
                    }
                case link:
                    if (course.getLink().toLowerCase().contains(value)) {
                        results.add(course);
                    }
            }
        }
        return results.isEmpty() ? Optional.empty() : Optional.of(results);
    }
}
