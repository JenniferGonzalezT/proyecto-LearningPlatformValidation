package com.duoc.LearningPlatformValidation.service;

import java.util.List;
import java.util.Optional;
import com.duoc.LearningPlatformValidation.model.Usuario;

public interface IUsuarioService {
    
    // Consultar todos los usuarios
    List<Usuario> obtenerTodos();
    
    // Consultar usuario por ID
    Optional<Usuario> obtenerPorId(Long id);
    
    // Buscar usuario por nombre
    List<Usuario> obtenerPorNombre(String nombre);

    // Registrar usuario
    Usuario crear(Usuario usuario);
    
    // Modificar usuario
    Usuario actualizar(Long id, Usuario usuario);
    
    // Eliminar usuario
    void eliminar(Long id);
}
