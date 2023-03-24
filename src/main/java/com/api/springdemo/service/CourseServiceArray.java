package com.api.springdemo.service;

import com.api.springdemo.exception.NotFoundException;
import com.api.springdemo.model.Course;
import com.api.springdemo.repository.ICourseRepositoryArray;
import com.api.springdemo.util.CourseKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CourseServiceArray implements ICourseServiceArray {

    @Autowired
    private ICourseRepositoryArray iCourseRepositoryArray;

    @Override
    public List<Course> list() {
        try {
            List<Course> courses = iCourseRepositoryArray.getAll();
            if (courses.isEmpty()) {
                throw new NotFoundException();
            }
            return courses;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Course create(Course course) {
        try {
            return iCourseRepositoryArray.create(course);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Course> get(String id) {
        try {
            Optional<Course> course = iCourseRepositoryArray.findById(id);
            if (course.isEmpty()) {
                throw new NotFoundException();
            }
            return course;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Course course, String id) {
        try {
            Optional<Course> find = iCourseRepositoryArray.findById(id);
            if (find.isEmpty()) {
                throw new NotFoundException();
            }
            iCourseRepositoryArray.update(course, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {
        try {
            Optional<Course> find = iCourseRepositoryArray.findById(id);
            if (find.isEmpty()) {
                throw new NotFoundException();
            }
            iCourseRepositoryArray.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Course> findBy(CourseKey by, String value) throws Exception {
        Optional<List<Course>> courses = iCourseRepositoryArray.findBy(by, value);
        if (courses.isEmpty()) {
            throw new Exception("Course not found!");
        }
        return courses.get();
    }
}
