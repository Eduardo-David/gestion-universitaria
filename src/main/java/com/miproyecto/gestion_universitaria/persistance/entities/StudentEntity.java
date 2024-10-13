package com.miproyecto.gestion_universitaria.persistance.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.miproyecto.gestion_universitaria.DTO.CourseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long studentId;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "students")
    private List<CourseEntity> courses=new ArrayList<>();

   /* @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
   // @JsonManagedReference
    private List<CourseEntity> courses=new ArrayList<>();
    */

   /* public void addCourse(CourseEntity course){
        courses.add(course);
        course.setStudent(this);
    }
    */
   public void addCourse(CourseEntity course) {
       courses.add(course);
       course.getStudents().add(this);
   }

}
