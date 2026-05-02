package com.duoc.LearningPlatformValidation.service;

import java.util.List;
import java.util.Optional;
import com.duoc.LearningPlatformValidation.model.Curso;

public interface ICursoService {
    
    // Consultar todos los cursos
    List<Curso> obtenerTodos();
    
    // Consultar curso por ID
    Optional<Curso> obtenerPorId(Long id);
    
    // Buscar curso por nombre
    List<Curso> buscarPorNombre(String nombre);
    
    // Registrar curso
    Curso crear(Curso curso);
    
    // Modificar curso
    Curso actualizar(Long id, Curso curso);
    
    // Eliminar curso
    void eliminar(Long id);
}
