package com.miproyecto.gestion_universitaria.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseWithTeacherDTO {

    private Long courseId;
    private String name;
    private String teacher;
}
