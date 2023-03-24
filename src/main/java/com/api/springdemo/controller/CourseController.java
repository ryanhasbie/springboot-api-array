package com.api.springdemo.controller;

import com.api.springdemo.model.Course;
import com.api.springdemo.model.request.CourseRequest;
import com.api.springdemo.model.response.PagingResponse;
import com.api.springdemo.model.response.SuccessResponse;
import com.api.springdemo.service.ICourseService;
import com.api.springdemo.util.constant.Operator;
import com.api.springdemo.util.specification.SearchCriteria;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@Validated
public class CourseController {
//    @Autowired
//    private ICourseServiceArray iCourseServiceArray;
    @Autowired
    private ICourseService iCourseService;
    @Autowired
    private ModelMapper modelMapper;

//    @GetMapping
//    public ResponseEntity getAllCourse() {
//        List<Course> courseList = iCourseService.list();
//        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get all course!", courseList));
//    }

    @PostMapping
    public ResponseEntity createCourse(@Valid @RequestBody CourseRequest courseRequest) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
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
//        Optional<Course> course = iCourseService.get(id);
        Course course = iCourseService.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get data by id!", course));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCourse(@PathVariable String id) {
//        iCourseServiceArray.delete(id);
        iCourseService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Delete successfully!", id));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCourse(@Valid @RequestBody CourseRequest courseRequest, @PathVariable String id) {
        Course existingCourse = modelMapper.map(courseRequest, Course.class);
//        iCourseServiceArray.update(existingCourse, id);
        iCourseService.update(existingCourse, id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Updated success!", courseRequest));
    }

    @GetMapping(params = {"key", "value"})
    public ResponseEntity getBy(@RequestParam String keyword, @RequestParam String value) throws Exception {
//        List<Course> courses = iCourseServiceArray.findBy(CourseKey.valueOf(key), value);
        List<Course> courses = iCourseService.getBy(keyword, value);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get course by!", courses));
    }

    @GetMapping
    public ResponseEntity getByPagination(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam(defaultValue = "ASC") String direction,
            @RequestParam(defaultValue = "title") String sortBy) {
        Page<Course> courses = iCourseService.getByPagination(page, size, direction, sortBy);
        return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Success get pagination", courses));
    }

    @GetMapping(params = {"key", "value", "operator"})
    public ResponseEntity getAllBy(@RequestParam("key") String key, @RequestParam("value") String value, @RequestParam("operator") String operator) {
        SearchCriteria searchCriteria = new SearchCriteria(key, Operator.valueOf(operator), value);
        List<Course> courses = iCourseService.listBy(searchCriteria);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get all course by", courses));
    }

}
