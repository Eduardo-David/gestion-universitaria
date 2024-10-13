package com.miproyecto.gestion_universitaria.DTO;

import com.miproyecto.gestion_universitaria.persistance.entities.StudentEntity;
import com.miproyecto.gestion_universitaria.persistance.entities.TeacherEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {

    private Long courseId;
    private String name;

}
