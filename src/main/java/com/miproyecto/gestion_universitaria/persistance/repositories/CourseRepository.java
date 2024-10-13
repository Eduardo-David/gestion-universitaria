package com.miproyecto.gestion_universitaria.persistance.repositories;

import com.miproyecto.gestion_universitaria.persistance.entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity,Long> {

}
