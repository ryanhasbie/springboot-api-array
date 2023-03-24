package com.api.springdemo.controller;

import com.api.springdemo.model.Course;
import com.api.springdemo.model.CourseType;
import com.api.springdemo.model.request.CourseTypeRequest;
import com.api.springdemo.model.response.PagingResponse;
import com.api.springdemo.model.response.SuccessResponse;
import com.api.springdemo.service.ICourseTypeService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course-type")
@Validated
public class CourseTypeController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ICourseTypeService iCourseTypeService;

    @PostMapping
    public ResponseEntity createType(@Valid @RequestBody CourseTypeRequest courseType) {
        CourseType newCourseType = modelMapper.map(courseType, CourseType.class);
        CourseType result = iCourseTypeService.create(newCourseType);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success created new course type", result));
    }

    @GetMapping
    public ResponseEntity getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "ASC") String direction,
            @RequestParam(defaultValue = "courseTypeName") String sortBy
    ) {
        Page<CourseType> courseTypes = iCourseTypeService.getAll(page, pageSize, direction, sortBy);
        return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Success get all course type", courseTypes));
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity getCourseById(@PathVariable("id") String id){
        List<Course> courseList = iCourseTypeService.getCourseById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get courses list",courseList));
    }

    @GetMapping("/{name}")
    public ResponseEntity getCourseTypeByName(@PathVariable("name") String name){
        List<CourseType> result = iCourseTypeService.findCourseType(name);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get by name", result));
    }
}