package com.duoc.LearningPlatformValidation.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.duoc.LearningPlatformValidation.model.Evaluacion;
import com.duoc.LearningPlatformValidation.service.IEvaluacionService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/evaluaciones")
@CrossOrigin(origins = "*")
public class EvaluacionController {

    private final IEvaluacionService evaluacionService;
    public EvaluacionController(IEvaluacionService evaluacionService) {
        this.evaluacionService = evaluacionService;
    }

    // GET - Consultar todas las evaluaciones
    @GetMapping
    public ResponseEntity<List<Evaluacion>> obtenerTodos() {
        List<Evaluacion> evaluaciones = evaluacionService.obtenerTodos();
        return ResponseEntity.ok(evaluaciones);
    }

    // GET - Consultar evaluaciones por curso
    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<Evaluacion>> obtenerPorCurso(@PathVariable Long cursoId) {
        List<Evaluacion> evaluaciones = evaluacionService.obtenerPorCurso(cursoId);
        return ResponseEntity.ok(evaluaciones);
    }

    // POST - Registrar evaluación
    @PostMapping
    public ResponseEntity<Evaluacion> crear(@Valid @RequestBody Evaluacion evaluacion) {
        try {
            Evaluacion nuevaEvaluacion = evaluacionService.crear(evaluacion);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaEvaluacion);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // PUT - Modificar evaluación
    @PutMapping("/{id}")
    public ResponseEntity<Evaluacion> actualizar(@PathVariable Long id, @Valid @RequestBody Evaluacion evaluacion) {
        try {
            Evaluacion evaluacionActualizada = evaluacionService.actualizar(id, evaluacion);
            return ResponseEntity.ok(evaluacionActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
