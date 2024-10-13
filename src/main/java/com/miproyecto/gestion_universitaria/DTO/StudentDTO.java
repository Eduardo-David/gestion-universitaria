package com.miproyecto.gestion_universitaria.DTO;

import com.miproyecto.gestion_universitaria.persistance.entities.CourseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    private Long courseId;

    private String name;

    private List<CourseWithTeacherDTO> courses=new ArrayList<>();
}
