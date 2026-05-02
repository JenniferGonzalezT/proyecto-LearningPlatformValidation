package com.duoc.LearningPlatformValidation.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.duoc.LearningPlatformValidation.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    // Validar que no se registren correos duplicados
    boolean existsByCorreo(String correo);

    // Buscar usuarios por nombre (con búsqueda parcial e insensible a mayúsculas)
    List<Usuario> findByNombreContainingIgnoreCase(String nombre);

    // Buscar usuarios por rol
    List<Usuario> findByRol(String rol);
}
