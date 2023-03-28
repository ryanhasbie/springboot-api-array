package com.api.springdemo.service;

import com.api.springdemo.exception.NotFoundException;
import com.api.springdemo.model.Course;
import com.api.springdemo.model.CourseInfo;
import com.api.springdemo.model.CourseType;
import com.api.springdemo.model.request.CourseRequest;
import com.api.springdemo.repository.ICourseInfoRepository;
import com.api.springdemo.repository.ICourseRepository;
import com.api.springdemo.repository.ICourseTypeRepository;
import com.api.springdemo.util.specification.SearchCriteria;
import com.api.springdemo.util.specification.Spec;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityExistsException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements ICourseService {
    @Autowired
    private ICourseRepository iCourseRepository;
    @Autowired
    private ICourseTypeRepository iCourseTypeRepository;
    @Autowired
    private ICourseInfoRepository iCourseInfoRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    IFileService fileService;


    @Override
    public List<Course> list() {
        List<Course> courses = iCourseRepository.findAll();
        if (courses.isEmpty()) {
            throw new NotFoundException();
        }
        return courses;
    }

    @Override
    public Course create(CourseRequest courseRequest){
//        try {
//            Optional<CourseType> courseType = iCourseTypeRepository.findById(course.getCourseType().getCourseTypeId());
//            if (courseType.isEmpty()) {
//                throw new NotFoundException("Course type not found");
//            }
//            course.setCourseType(courseType.get());
//            return iCourseRepository.save(course);
//        } catch (DataIntegrityViolationException e) {
//            throw new EntityExistsException();
//        }
        try {
            String filePath = "";

            Optional<CourseType> courseType = iCourseTypeRepository.findById(courseRequest.getCourseType());

            if (courseType.isEmpty()) {
                throw new NotFoundException();
            }
            if (!courseRequest.getFile().isEmpty()) {
                filePath = fileService.uploadFile(courseRequest.getFile());
            }
            CourseInfo courseInfo = modelMapper.map(courseRequest, CourseInfo.class);
            CourseInfo courseInfoRequest = iCourseInfoRepository.save(courseInfo);
            Course course = modelMapper.map(courseRequest, Course.class);
            course.setTitle(courseRequest.getTitle());
            course.setDescription(courseRequest.getDescription());
            course.setLink(filePath);
            course.setCourseType(courseType.get());
            course.setCourseInfo(courseInfoRequest);
            return iCourseRepository.save(course);
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistsException();
        }
    }

    @Override
    public Course get(String id){
        Optional<Course> course = iCourseRepository.findById(id);
        if (course.isEmpty()) {
            throw new NotFoundException("Course not found!");
        }
        return course.get();
    }

    @Override
    public void update(Course course, String id) {
        try {
            Course existingCourse = get(id);
            course.setCourseId(existingCourse.getCourseId());
            iCourseRepository.save(course);
        } catch (Exception e) {
            throw new NotFoundException("Update failed, course id is not found!");
        }
    }

    @Override
    public void delete(String id) {
        try {
            Course existingCourse = get(id);
            iCourseRepository.delete(existingCourse);
        } catch (Exception e) {
            throw new NotFoundException("Delete failed, course id is not found!");
        }
    }

    @Override
    public Page<Course> getByPagination(int page, int size, String direction, String sortBy) {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page - 1), size, sort);
        return iCourseRepository.findAll(pageable);
    }

    @Override
    public List<Course> getByTitle(String value) {
        List<Course> courses = iCourseRepository.findByTitle(value);
        if (courses.isEmpty()) {
            throw new NotFoundException("Course with title "+value+" not found!");
        }
        return courses;
    }

    @Override
    public List<Course> getByDesc(String value)  {
        List<Course> courses = iCourseRepository.findByDescription(value);
        if (courses.isEmpty()) {
            throw new NotFoundException("Course with description "+value+" not found!");
        }
        return courses;
    }

    @Override
    public List<Course> getBy(String keyword, String value) {
        switch (keyword) {
            case "title":
                return getByTitle(value);
            case "description":
                return getByDesc(value);
            default:
                return iCourseRepository.findAll();
        }
    }

    @Override
    public List<Course> listBy(SearchCriteria searchCriteria) {
        Specification specification = new Spec<Course>().findBy(searchCriteria);
        List<Course> courses = iCourseRepository.findAll(specification);
        return courses;
    }

    @Override
    public Resource download(String id) {
        Optional<Course> course = iCourseRepository.findById(id);
        String filename = course.get().getLink();
        return fileService.downloadFile(filename);
    }

//    public Resource download (String link, String id) {
//        try {
//            Resource resource = new UrlResource(get(id).getLink());
//        } catch (MalformedURLException e) {
//            throw
//        }
//    }
}
