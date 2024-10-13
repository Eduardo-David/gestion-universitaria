package com.miproyecto.gestion_universitaria.services;

import com.miproyecto.gestion_universitaria.DTO.CourseDTO;
import com.miproyecto.gestion_universitaria.DTO.CourseWithTeacherDTO;
import com.miproyecto.gestion_universitaria.DTO.StudentDTO;
import com.miproyecto.gestion_universitaria.persistance.entities.CourseEntity;
import com.miproyecto.gestion_universitaria.persistance.entities.StudentEntity;
import com.miproyecto.gestion_universitaria.persistance.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("courseService")
public class CourseServiceImpl{

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentServiceImpl studentService;

    public List<CourseEntity> getAllCourse() {
        return courseRepository.findAll();
    }

    public CourseDTO convertToDTO(CourseEntity courseEntity) {
        return new CourseDTO(courseEntity.getCourseId(), courseEntity.getName());
    }

    public CourseWithTeacherDTO convertToDtoWithTeacher(CourseEntity courseEntity) {
        String teacher = (courseEntity.getTeacher() != null) ? courseEntity.getTeacher().getName() : "No teacher assigned";
        return new CourseWithTeacherDTO(courseEntity.getCourseId(), courseEntity.getName() , teacher);
    }

    public void saveCourse(CourseEntity course) {
        CourseEntity materiaEntity=new CourseEntity();
        materiaEntity.setName(course.getName());
        CourseEntity materiaGuardada =  courseRepository.save(materiaEntity);
        course.setCourseId(materiaGuardada.getCourseId());
    }

    public Optional<CourseEntity> getByIdCourse(Long id) {
        return courseRepository.findById(id);
    }
    
}
