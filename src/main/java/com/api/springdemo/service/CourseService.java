package com.api.springdemo.service;

import com.api.springdemo.exception.NotFoundException;
import com.api.springdemo.model.Course;
import com.api.springdemo.model.request.CourseRequest;
import com.api.springdemo.model.response.ErrorResponse;
import com.api.springdemo.repository.ICourseRepository;
import com.api.springdemo.util.CourseKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CourseService implements ICourseService{

    @Autowired
    private ICourseRepository iCourseRepository;

    @Override
    public List<Course> list() {
        try {
            List<Course> courses = iCourseRepository.getAll();
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
            return iCourseRepository.create(course);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Course> get(String id) {
        try {
            Optional<Course> course = iCourseRepository.findById(id);
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
            Optional<Course> find = iCourseRepository.findById(id);
            if (find.isEmpty()) {
                throw new NotFoundException();
            }
            iCourseRepository.update(course, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {
        try {
            Optional<Course> find = iCourseRepository.findById(id);
            if (find.isEmpty()) {
                throw new NotFoundException();
            }
            iCourseRepository.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Course> findBy(CourseKey by, String value) throws Exception {
        Optional<List<Course>> courses = iCourseRepository.findBy(by, value);
        if (courses.isEmpty()) {
            throw new Exception("Course not found!");
        }
        return courses.get();
    }
}
