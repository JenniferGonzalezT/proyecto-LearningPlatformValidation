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
import com.duoc.LearningPlatformValidation.model.Curso;
import com.duoc.LearningPlatformValidation.service.ICursoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cursos")
@CrossOrigin(origins = "*")
public class CursoController {

    private final ICursoService cursoService;

    public CursoController(ICursoService cursoService) {
        this.cursoService = cursoService;
    }

    // GET - Consultar todos los cursos
    @GetMapping
    public ResponseEntity<List<Curso>> obtenerTodos() {
        List<Curso> cursos = cursoService.obtenerTodos();
        return ResponseEntity.ok(cursos);
    }

    // GET - Consultar curso por ID
    @GetMapping("/{id}")
    public ResponseEntity<Curso> obtenerPorId(@PathVariable Long id) {
        Optional<Curso> curso = cursoService.obtenerPorId(id);
        if (curso.isPresent()) {
            return ResponseEntity.ok(curso.get());
        }
        return ResponseEntity.notFound().build();
    }

    // POST - Registrar curso
    @PostMapping
    public ResponseEntity<Curso> crear(@Valid @RequestBody Curso curso) {
        try {
            Curso nuevoCurso = cursoService.crear(curso);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCurso);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // PUT - Modificar curso
    @PutMapping("/{id}")
    public ResponseEntity<Curso> actualizar(@PathVariable Long id, @Valid @RequestBody Curso curso) {
        try {
            Curso cursoActualizado = cursoService.actualizar(id, curso);
            return ResponseEntity.ok(cursoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE - Eliminar curso
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            cursoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
