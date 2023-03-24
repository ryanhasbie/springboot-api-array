package com.api.springdemo.service;

import com.api.springdemo.exception.NotFoundException;
import com.api.springdemo.model.CourseInfo;
import com.api.springdemo.repository.ICourseInfoRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class CourseInfoService implements ICourseInfoService{
    @Autowired
    private ICourseInfoRepository iCourseInfoRepository;

    @Override
    public CourseInfo create(CourseInfo courseInfo) {
        try {
            return iCourseInfoRepository.save(courseInfo);
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistsException();
        }
    }

    @Override
    public CourseInfo getById(String id) {
        Optional<CourseInfo> courseInfo = iCourseInfoRepository.findById(id);
        if (courseInfo.isEmpty()) {
            throw new NotFoundException("Course info not found!");
        }
        return courseInfo.get();
    }

    @Override
    public Page<CourseInfo> getAll(int page, int pageSize, String direction, String sortBy) {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page-1), pageSize, sort);
        Page<CourseInfo> result = iCourseInfoRepository.findAll(pageable);
        if (result.isEmpty()) {
            throw new NotFoundException();
        }
        return result;
    }
}
