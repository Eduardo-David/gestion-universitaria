package com.miproyecto.gestion_universitaria.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO {

    private Long teacherId;
    private String name;
    private List<CourseDTO> courses=new ArrayList<>() ;
    public void addCourse(CourseDTO course) {
        courses.add(course);
    }
}
