package com.miproyecto.gestion_universitaria.services;

import com.miproyecto.gestion_universitaria.DTO.CourseDTO;
import com.miproyecto.gestion_universitaria.DTO.TeacherDTO;
import com.miproyecto.gestion_universitaria.persistance.entities.TeacherEntity;
import com.miproyecto.gestion_universitaria.persistance.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("teacherService")
public class TeacherServiceImpl {

    @Autowired
    private TeacherRepository teacherRepository;
    
    public Optional<TeacherEntity> getTeacherById(Long id) {
        return  teacherRepository.findById(id);
    }

    public TeacherDTO convertToDTO(TeacherEntity teacher) {
        List<CourseDTO> courseDTOs = teacher.getCourseEntities().stream()
                .map(course -> new CourseDTO(course.getCourseId(), course.getName()))
                .toList(); // Usando toList() de Java 17 para convertir a List

        return new TeacherDTO(teacher.getTeacherId(), teacher.getName(), courseDTOs);
    }

    public void saveTeacher(TeacherEntity teacher) {
        TeacherEntity teacherEntity= new TeacherEntity();
        teacherEntity.setName(teacher.getName());

     TeacherEntity teacherSave= teacherRepository.save(teacherEntity);
     teacher.setTeacherId(teacherSave.getTeacherId());
    }

}
