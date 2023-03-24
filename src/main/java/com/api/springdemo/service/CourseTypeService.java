package com.api.springdemo.service;

import com.api.springdemo.exception.ExistException;
import com.api.springdemo.exception.NotFoundException;
import com.api.springdemo.model.Course;
import com.api.springdemo.model.CourseType;
import com.api.springdemo.repository.ICourseTypeRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CourseTypeService implements ICourseTypeService{

    @Autowired
    private ICourseTypeRepository iCourseTypeRepository;

    @Override
    public CourseType create(CourseType courseType) {
        try {
            return iCourseTypeRepository.save(courseType);
        } catch (DataIntegrityViolationException e) {
            throw new ExistException();
        }
    }

    @Override
    public CourseType getById(String id)  {
        Optional<CourseType> courseType = iCourseTypeRepository.findById(id);
        if (courseType.isEmpty()) {
            throw new NotFoundException("Course type not found");
        }
        return courseType.get();
    }

    @Override
    public Page<CourseType> getAll(int page, int pageSize, String direction, String sortBy)  {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction),sortBy);
        Pageable pageable = PageRequest.of((page-1), pageSize, sort);
        Page<CourseType> courseTypes = iCourseTypeRepository.findAll(pageable);
        if (courseTypes.isEmpty()) {
            throw new NotFoundException();
        }
        return courseTypes;
    }

    @Override
    public List<Course> getCourseById(String id) {
        Optional<CourseType> courseType = iCourseTypeRepository.findById(id);
        return new ArrayList<>(courseType.get().getCourses());
    }

    @Override
    public List<CourseType> findCourseType(String name) {
        return null;
    }
}
