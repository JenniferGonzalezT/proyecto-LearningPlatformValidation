package com.duoc.LearningPlatformValidation.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.duoc.LearningPlatformValidation.model.Usuario;
import com.duoc.LearningPlatformValidation.service.IUsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final IUsuarioService usuarioService;   
    public UsuarioController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // GET - Consultar todos los usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodos() {
        List<Usuario> usuarios = usuarioService.obtenerTodos();
        return ResponseEntity.ok(usuarios);
    }

    // GET - Consultar usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.obtenerPorId(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        }
        return ResponseEntity.notFound().build();
    }

    // POST - Registrar usuario
    @PostMapping
    public ResponseEntity<Usuario> crear(@Valid @RequestBody Usuario usuario) {
        try {
            Usuario nuevoUsuario = usuarioService.crear(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // PUT - Modificar usuario
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable Long id, @Valid @RequestBody Usuario usuario) {
        try {
            Usuario usuarioActualizado = usuarioService.actualizar(id, usuario);
            return ResponseEntity.ok(usuarioActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE - Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            usuarioService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
