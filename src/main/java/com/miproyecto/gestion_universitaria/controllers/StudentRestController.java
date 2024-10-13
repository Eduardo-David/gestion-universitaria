package com.miproyecto.gestion_universitaria.controllers;

import com.miproyecto.gestion_universitaria.DTO.CourseDTO;
import com.miproyecto.gestion_universitaria.DTO.StudentDTO;
import com.miproyecto.gestion_universitaria.persistance.entities.CourseEntity;
import com.miproyecto.gestion_universitaria.persistance.entities.StudentEntity;
import com.miproyecto.gestion_universitaria.persistance.repositories.StudentRepository;
import com.miproyecto.gestion_universitaria.services.CourseServiceImpl;
import com.miproyecto.gestion_universitaria.services.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentRestController {

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private CourseServiceImpl courseService;

    @Autowired
    private StudentRepository studentRepository;


    @PostMapping
    public ResponseEntity<?> studentCreate(@RequestBody StudentEntity student) {
        studentService.saveStudent(student);
        URI localitation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(student.getStudentId())
                .toUri();
        StudentDTO studentDTO = studentService.convertToDTO(student);
        return ResponseEntity.created(localitation).body(studentDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id){
        try {
            StudentEntity studentEntity = studentService.getStudentById(id).get();
            StudentDTO studentDTO = studentService.convertToDTO(studentEntity);
            return ResponseEntity.ok(studentDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{courseId}/{studentId}")
    public ResponseEntity<?> saveCourse(@PathVariable Long courseId, @PathVariable Long studentId){
        CourseEntity course = courseService.getByIdCourse(courseId).get();
        StudentEntity student = studentService.getStudentById(studentId).get();
        student.addCourse(course);
        studentRepository.save(student);

        StudentDTO studentDTO = studentService.convertToDTO(student);
        return ResponseEntity.ok(studentDTO);
    }
}

