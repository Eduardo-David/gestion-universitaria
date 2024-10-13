package com.miproyecto.gestion_universitaria.persistance.entities;

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
public class TeacherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long teacherId;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    //@JsonManagedReference
    private List<CourseEntity> courseEntities =new ArrayList<>() ;

    public void addCourse(CourseEntity course){
        courseEntities.add(course);
        course.setTeacher(this);
    }

}
