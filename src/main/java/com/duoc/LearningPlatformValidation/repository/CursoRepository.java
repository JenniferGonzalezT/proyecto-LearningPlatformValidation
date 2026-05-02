package com.duoc.LearningPlatformValidation.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.duoc.LearningPlatformValidation.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long>{
    // Buscar cursos por nombre (con búsqueda parcial e insensible a mayúsculas)
    List<Curso> findByNombreContainingIgnoreCase(String nombre);

    // Consultar cursos por profesor
    List<Curso> findByProfesorId(Long profesorId);
}
