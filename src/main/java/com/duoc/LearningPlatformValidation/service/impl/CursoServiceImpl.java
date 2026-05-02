package com.duoc.LearningPlatformValidation.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.duoc.LearningPlatformValidation.model.Curso;
import com.duoc.LearningPlatformValidation.model.Usuario;
import com.duoc.LearningPlatformValidation.repository.CursoRepository;
import com.duoc.LearningPlatformValidation.repository.UsuarioRepository;
import com.duoc.LearningPlatformValidation.service.ICursoService;

@Service
public class CursoServiceImpl implements ICursoService {

    private final CursoRepository cursoRepository;
    private final UsuarioRepository usuarioRepository;

    public CursoServiceImpl(CursoRepository cursoRepository, UsuarioRepository usuarioRepository){
        this.cursoRepository = cursoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Curso> obtenerTodos() {
        return cursoRepository.findAll();
    }

    @Override
    public Optional<Curso> obtenerPorId(Long id) {
        return cursoRepository.findById(id);
    }

    @Override
    public List<Curso> buscarPorNombre(String nombre) {
        return cursoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public Curso crear(Curso curso) {
        Usuario profesor = usuarioRepository.findById(curso.getProfesor().getId())
            .orElseThrow(() -> new IllegalArgumentException(
                "Profesor no encontrado con ID: " + curso.getProfesor().getId()));
        if(!"profesor".equalsIgnoreCase(profesor.getRol())){
            throw new IllegalArgumentException(
                "El usuario con ID: " + curso.getProfesor().getId() + " no posee rol de profesor");
        }
        curso.setProfesor(profesor);
        return cursoRepository.save(curso);
    }

    @Override
    public Curso actualizar(Long id, Curso curso) {
        return cursoRepository.findById(id)
            .map(cursoExistente -> {
                cursoExistente.setNombre(curso.getNombre());
                cursoExistente.setDescripcion(curso.getDescripcion());

                if(curso.getProfesor() != null && curso.getProfesor().getId() != null){
                    Usuario profesor = usuarioRepository.findById(curso.getProfesor().getId())
                        .orElseThrow(() -> new IllegalArgumentException(
                            "Profesor no encontrado con ID: " + curso.getProfesor().getId()));
                    if(!"profesor".equalsIgnoreCase(profesor.getRol())){
                        throw new IllegalArgumentException(
                            "El usuario con ID: " + curso.getProfesor().getId() + " no posee rol de profesor");
                    }
                    cursoExistente.setProfesor(profesor);
                }
                return cursoRepository.save(cursoExistente);
            })
            .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado con ID: " + id));
    }

    @Override
    public void eliminar(Long id) {
        if (!cursoRepository.existsById(id)) {
            throw new IllegalArgumentException("Curso no encontrado con ID: " + id);
        }
        cursoRepository.deleteById(id);
    }
}
