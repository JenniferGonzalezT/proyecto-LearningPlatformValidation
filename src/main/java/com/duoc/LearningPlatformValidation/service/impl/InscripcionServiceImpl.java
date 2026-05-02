package com.duoc.LearningPlatformValidation.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.duoc.LearningPlatformValidation.model.Curso;
import com.duoc.LearningPlatformValidation.model.Inscripcion;
import com.duoc.LearningPlatformValidation.model.Usuario;
import com.duoc.LearningPlatformValidation.repository.CursoRepository;
import com.duoc.LearningPlatformValidation.repository.InscripcionRepository;
import com.duoc.LearningPlatformValidation.repository.UsuarioRepository;
import com.duoc.LearningPlatformValidation.service.IInscripcionService;

@Service
public class InscripcionServiceImpl implements IInscripcionService {

    private final InscripcionRepository inscripcionRepository;
    private final CursoRepository cursoRepository;
    private final UsuarioRepository usuarioRepository;

    public InscripcionServiceImpl(InscripcionRepository inscripcionRepository, UsuarioRepository usuarioRepository, CursoRepository cursoRepository){
        this.inscripcionRepository = inscripcionRepository;
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
    }

    @Override
    public List<Inscripcion> obtenerPorCurso(Long cursoId) {
        if(!cursoRepository.existsById(cursoId)){
            throw new IllegalArgumentException("Curso no encontrado con ID: " + cursoId);
        }
        return inscripcionRepository.findByCursoId(cursoId);
    }

    @Override
    public Inscripcion crear(Inscripcion inscripcion) {
        Curso curso = cursoRepository.findById(inscripcion.getCurso().getId())
            .orElseThrow(() -> new IllegalArgumentException(
                "Curso no encontrado con ID: " + inscripcion.getCurso().getId()));
        Usuario estudiante = usuarioRepository.findById(inscripcion.getEstudiante().getId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Estudiante no encontrado con ID: " + inscripcion.getEstudiante().getId()));
        
        if(!"estudiante".equalsIgnoreCase(estudiante.getRol())) {
            throw new IllegalArgumentException(
                    "El usuario con ID: " + estudiante.getId() + " no tiene rol de alumno");
        }

        if(inscripcionRepository.existsByEstudianteIdAndCursoId(estudiante.getId(), curso.getId())){
            throw new IllegalArgumentException(
                    "El estudiante con ID: " + estudiante.getId() + " ya está inscrito en el curso con ID: " + curso.getId());
        }
        inscripcion.setCurso(curso);
        inscripcion.setEstudiante(estudiante);
        return inscripcionRepository.save(inscripcion);
    }

    @Override
    public void eliminar(Long id) {
        if (!inscripcionRepository.existsById(id)) {
            throw new IllegalArgumentException("Inscripción no encontrada con ID: " + id);
        }
        inscripcionRepository.deleteById(id);
    }
}