package com.duoc.LearningPlatformValidation.service;

import java.util.List;
import com.duoc.LearningPlatformValidation.model.Evaluacion;

public interface IEvaluacionService {
    
    // Consultar todas las evaluaciones
    List<Evaluacion> obtenerTodos();
    
    // Consultar evaluaciones por curso
    List<Evaluacion> obtenerPorCurso(Long cursoId);
    
    // Registrar evaluación
    Evaluacion crear(Evaluacion evaluacion);
    
    // Modificar evaluación
    Evaluacion actualizar(Long id, Evaluacion evaluacion);
}
