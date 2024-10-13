package com.miproyecto.gestion_universitaria.services;

import com.miproyecto.gestion_universitaria.DTO.CourseDTO;
import com.miproyecto.gestion_universitaria.DTO.CourseWithTeacherDTO;
import com.miproyecto.gestion_universitaria.DTO.StudentDTO;
import com.miproyecto.gestion_universitaria.persistance.entities.CourseEntity;
import com.miproyecto.gestion_universitaria.persistance.entities.StudentEntity;
import com.miproyecto.gestion_universitaria.persistance.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("studentService")
public class StudentServiceImpl  {

    @Autowired
    private StudentRepository universitarioRepository;


    public StudentDTO convertToDTO(StudentEntity student) {
       /* List<CourseWithTeacherDTO> courseDTOs = student.getCourses().stream()
                .map(course -> new CourseWithTeacherDTO(course.getCourseId(), course.getName(), (course.getTeacher() != null)?course.getTeacher().getName():"No teacher assigned"))
                .toList(); // Usando toList() de Java 17 para convertir a List

        */
        List<CourseWithTeacherDTO> courseDTOs = student.getCourses().stream()
                .map(course -> {
                    String teacherName = (course.getTeacher() != null) ? course.getTeacher().getName() : "No teacher assigned";
                    return new CourseWithTeacherDTO(course.getCourseId(), course.getName(), teacherName);
                })
                .toList(); // Using toList() from Java 17 for conversion

        return new StudentDTO(student.getStudentId(), student.getName(), courseDTOs);
    }


    public void saveStudent(StudentEntity student) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setName(student.getName());

        StudentEntity universitarioGuardado = universitarioRepository.save(studentEntity);
        student.setStudentId(universitarioGuardado.getStudentId());
    }
    
    public Optional<StudentEntity> getStudentById(Long id) {
        return universitarioRepository.findById(id);
    }
}
