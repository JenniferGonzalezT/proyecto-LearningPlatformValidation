package com.duoc.LearningPlatformValidation.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.duoc.LearningPlatformValidation.model.Inscripcion;
import com.duoc.LearningPlatformValidation.service.IInscripcionService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/inscripciones")
@CrossOrigin(origins = "*")
public class InscripcionController {

    private final IInscripcionService inscripcionService;
    public InscripcionController(IInscripcionService inscripcionService) {
        this.inscripcionService = inscripcionService;
    }

    // GET - Consultar inscripciones por curso
    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<Inscripcion>> obtenerPorCurso(@PathVariable Long cursoId) {
        List<Inscripcion> inscripciones = inscripcionService.obtenerPorCurso(cursoId);
        return ResponseEntity.ok(inscripciones);
    }

    // POST - Registrar inscripción
    @PostMapping
    public ResponseEntity<Inscripcion> crear(@Valid @RequestBody Inscripcion inscripcion) {
        try {
            Inscripcion nuevaInscripcion = inscripcionService.crear(inscripcion);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaInscripcion);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // DELETE - Eliminar inscripción
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            inscripcionService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
