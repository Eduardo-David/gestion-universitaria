package com.miproyecto.gestion_universitaria.controllers;

import com.miproyecto.gestion_universitaria.DTO.CourseDTO;
import com.miproyecto.gestion_universitaria.DTO.CourseWithTeacherDTO;
import com.miproyecto.gestion_universitaria.persistance.entities.CourseEntity;
import com.miproyecto.gestion_universitaria.services.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseRestController {

    @Autowired
    CourseServiceImpl courseService;

    @GetMapping
    public ResponseEntity<?> readAllCourse(){
        //return ResponseEntity.ok(courseService.getAllCourse());
        List<CourseWithTeacherDTO> courseDTOs = courseService.getAllCourse().stream()
                .map(course -> courseService.convertToDtoWithTeacher(course))
                .toList();

        return ResponseEntity.ok(courseDTOs);
    }

    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody CourseEntity course){
        try {
            courseService.saveCourse(course);
            URI localitation = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(course.getCourseId())
                    .toUri();
            CourseDTO courseDTO = courseService.convertToDTO(course);
            return ResponseEntity.created(localitation).body(courseDTO);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }
}
