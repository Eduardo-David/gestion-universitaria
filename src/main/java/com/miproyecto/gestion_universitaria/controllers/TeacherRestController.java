package com.miproyecto.gestion_universitaria.controllers;

import com.miproyecto.gestion_universitaria.DTO.TeacherDTO;
import com.miproyecto.gestion_universitaria.persistance.entities.CourseEntity;
import com.miproyecto.gestion_universitaria.persistance.entities.StudentEntity;
import com.miproyecto.gestion_universitaria.persistance.entities.TeacherEntity;
import com.miproyecto.gestion_universitaria.persistance.repositories.TeacherRepository;
import com.miproyecto.gestion_universitaria.services.CourseServiceImpl;
import com.miproyecto.gestion_universitaria.services.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/teachers")
public class TeacherRestController {

    @Autowired
    private TeacherServiceImpl teacherService;

    @Autowired
    private CourseServiceImpl courseService;

    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping("/{id}")
    public ResponseEntity<?> readTeacherById(@PathVariable Long id){
        try {
            TeacherEntity teacherEntity = teacherService.getTeacherById(id).get();
            TeacherDTO teacherDTO = teacherService.convertToDTO(teacherEntity);
            return ResponseEntity.ok(teacherDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createTeacher(@RequestBody TeacherEntity teacher){
            teacherService.saveTeacher(teacher);
            URI localitation = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(teacher.getTeacherId())
                    .toUri();
            TeacherDTO teacherDTO = teacherService.convertToDTO(teacher);
            return ResponseEntity.created(localitation).body(teacherDTO);
    }


    @PutMapping("/{courseId}/{teacherId}")
    public ResponseEntity<?> saveCourse(@PathVariable Long courseId, @PathVariable Long teacherId){
        CourseEntity course = courseService.getByIdCourse(courseId).get();
        TeacherEntity teacher = teacherService.getTeacherById(teacherId).get();
        teacher.addCourse(course);
        teacherRepository.save(teacher);
        TeacherDTO teacherDTO = teacherService.convertToDTO(teacher);
        return ResponseEntity.ok(teacherDTO);
    }
}
