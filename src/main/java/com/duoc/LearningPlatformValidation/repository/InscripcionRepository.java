package com.duoc.LearningPlatformValidation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duoc.LearningPlatformValidation.model.Inscripcion;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    
    // Consultar inscripciones por curso
    List<Inscripcion> findByCursoId(Long cursoId);

    // Verificar si el estudiante ya está en el curso
    boolean existsByEstudianteIdAndCursoId(Long estudianteId, Long cursoId);
}
