package com.duoc.LearningPlatformValidation.service;

import java.util.List;
import com.duoc.LearningPlatformValidation.model.Inscripcion;

public interface IInscripcionService {
    
    // Consultar inscripciones por curso
    List<Inscripcion> obtenerPorCurso(Long cursoId);
    
    // Registrar inscripción
    Inscripcion crear(Inscripcion inscripcion);
    
    // Eliminar inscripción
    void eliminar(Long id);
}
