package com.duoc.LearningPlatformValidation.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.duoc.LearningPlatformValidation.model.Usuario;
import com.duoc.LearningPlatformValidation.repository.UsuarioRepository;
import com.duoc.LearningPlatformValidation.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public List<Usuario> obtenerPorNombre(String nombre){
        return usuarioRepository.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public Usuario crear(Usuario usuario) {
        if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
            throw new IllegalArgumentException("El correo ya está registrado " + usuario.getCorreo());
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario actualizar(Long id, Usuario usuario) {
        return usuarioRepository.findById(id)
            .map(usuarioExistente -> {
                usuarioExistente.setNombre(usuario.getNombre());
                usuarioExistente.setCorreo(usuario.getCorreo());
                usuarioExistente.setRol(usuario.getRol());
                return usuarioRepository.save(usuarioExistente);
            })
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + id));
    }

    @Override
    public void eliminar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuario no encontrado con ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }
}
