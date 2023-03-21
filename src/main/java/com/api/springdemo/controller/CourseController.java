package com.api.springdemo.controller;

import com.api.springdemo.model.Course;
import com.api.springdemo.model.request.CourseRequest;
import com.api.springdemo.model.response.ErrorResponse;
import com.api.springdemo.model.response.SuccessResponse;
import com.api.springdemo.service.ICourseService;
import com.api.springdemo.util.CourseKey;
import com.api.springdemo.util.validation.JwtUtil;
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
    private ICourseService iCourseService;
    @Autowired
    private ModelMapper modelMapper;
    private JwtUtil jwtUtil;
    @Autowired
    public CourseController(ICourseService iCourseService, ModelMapper modelMapper, JwtUtil jwtUtil) {
        this.iCourseService = iCourseService;
        this.modelMapper = modelMapper;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping
    public ResponseEntity getAllCourse() {
        String token = jwtUtil.generateToken("Enigma");
        System.out.println("Token: " + token);
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
        Boolean isValid = jwtUtil.validateToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJFbmlnbWEiLCJpYXQiOjE2NzkzNjkxMTgsImV4cCI6MTY3OTM2OTYxOH0.Vz_fMWuBpH0JE-YDDsk2X93UyJz165Jc4L31CgDPcfc");
        System.out.println("Valid: " + isValid);
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
