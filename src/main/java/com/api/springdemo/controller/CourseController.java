package com.api.springdemo.controller;

import com.api.springdemo.model.Course;
import com.api.springdemo.model.request.CourseRequest;
import com.api.springdemo.model.response.ErrorResponse;
import com.api.springdemo.model.response.SuccessResponse;
import com.api.springdemo.service.ICourseService;
import com.api.springdemo.util.CourseKey;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
@Validated
public class CourseController {
    @Autowired
    ICourseService iCourseService;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity getAllCourse() {
        List<Course> courseList = iCourseService.list();
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get all course!", courseList));
    }

    @PostMapping
    public ResponseEntity createCourse(@Valid @RequestBody CourseRequest courseRequest) {
        Course newCourse = modelMapper.map(courseRequest, Course.class);
//        Course newCourse = new Course();
//        newCourse.setTitle(courseRequest.getTitle());
//        newCourse.setDescription(courseRequest.getDescription());
//        newCourse.setLink(courseRequest.getLink());
        Course result = iCourseService.create(newCourse);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success create course!", result));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable String id) {
        Optional<Course> course = iCourseService.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get data by id!", course));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCourse(@PathVariable String id) {
        iCourseService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully!");
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCourse(@Valid @RequestBody CourseRequest courseRequest, @PathVariable String id) {
        Course existingCourse = modelMapper.map(courseRequest, Course.class);
        iCourseService.update(existingCourse, id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Updated success!", courseRequest));
    }

    @GetMapping(params = {"key", "value"})
    public ResponseEntity getBy(@RequestParam String key, @RequestParam String value) throws Exception {
        List<Course> courses = iCourseService.findBy(CourseKey.valueOf(key), value);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get course by!", courses));
    }
}
