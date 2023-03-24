package com.api.springdemo.controller;

import com.api.springdemo.model.CourseInfo;
import com.api.springdemo.model.request.CourseInfoRequest;
import com.api.springdemo.model.response.PagingResponse;
import com.api.springdemo.model.response.SuccessResponse;
import com.api.springdemo.service.ICourseInfoService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course-info")
@Validated
public class CourseInfoController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ICourseInfoService iCourseInfoService;

    @PostMapping
    public ResponseEntity createInfo(@Valid @RequestBody CourseInfoRequest courseInfo) throws Exception{
        CourseInfo newCourseInfo = modelMapper.map(courseInfo, CourseInfo.class);
        CourseInfo result = iCourseInfoService.create(newCourseInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success created new course info", result));
    }

    @GetMapping
    ResponseEntity getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "2") int pageSize,
            @RequestParam(defaultValue = "ASC") String direction,
            @RequestParam(defaultValue = "level") String sortBy
    ) {
        Page<CourseInfo> courseInfos = iCourseInfoService.getAll(page, pageSize, direction, sortBy);
        return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Success get all course info", courseInfos));
    }
}
