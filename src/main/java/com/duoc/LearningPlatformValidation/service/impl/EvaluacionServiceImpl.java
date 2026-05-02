package com.duoc.LearningPlatformValidation.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.duoc.LearningPlatformValidation.model.Evaluacion;
import com.duoc.LearningPlatformValidation.model.Curso;
import com.duoc.LearningPlatformValidation.repository.CursoRepository;
import com.duoc.LearningPlatformValidation.repository.EvaluacionRepository;
import com.duoc.LearningPlatformValidation.service.IEvaluacionService;

@Service
public class EvaluacionServiceImpl implements IEvaluacionService {

    private final EvaluacionRepository evaluacionRepository;
    private final CursoRepository cursoRepository;

    public EvaluacionServiceImpl(EvaluacionRepository evaluacionRepository, CursoRepository cursoRepository){
        this.evaluacionRepository = evaluacionRepository;
        this.cursoRepository = cursoRepository;
    }

    @Override
    public List<Evaluacion> obtenerTodos() {
        return evaluacionRepository.findAll();
    }

    @Override
    public List<Evaluacion> obtenerPorCurso(Long cursoId) {
        if(!cursoRepository.existsById(cursoId)){
            throw new IllegalArgumentException("Curso no encontrado con ID: " + cursoId);
        }
        return evaluacionRepository.findByCursoId(cursoId);
    }

    @Override
    public Evaluacion crear(Evaluacion evaluacion) {
        Curso curso = cursoRepository.findById(evaluacion.getCurso().getId())
            .orElseThrow(() -> new IllegalArgumentException(
                "Curso no encontrado con ID: " + evaluacion.getCurso().getId()));
        evaluacion.setCurso(curso);
        return evaluacionRepository.save(evaluacion);
    }

    @Override
    public Evaluacion actualizar(Long id, Evaluacion evaluacion) {
        return evaluacionRepository.findById(id)
            .map(evaluacionExistente -> {
                evaluacionExistente.setNombre(evaluacion.getNombre());
                evaluacionExistente.setPuntajeMaximo(evaluacion.getPuntajeMaximo());
                evaluacionExistente.setFechaAplicacion(evaluacion.getFechaAplicacion());

                if(evaluacion.getCurso() != null && evaluacion.getCurso().getId() != null){
                    Curso curso = cursoRepository.findById(evaluacion.getCurso().getId())
                        .orElseThrow(() -> new IllegalArgumentException(
                            "Curso no encontrado con ID: " + evaluacion.getCurso().getId()));
                    evaluacionExistente.setCurso(curso);
                }
                return evaluacionRepository.save(evaluacionExistente);
            })
            .orElseThrow(() -> new IllegalArgumentException("Evaluación no encontrada con ID: " + id));
    }
}
