package com.cursos.cursosapi.service;

import java.time.LocalDate;
import java.util.List;

import com.cursos.cursosapi.model.Curso;
import com.cursos.cursosapi.repository.CursoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    private final CursoRepository repository;

    public CursoService(CursoRepository repository) {
        this.repository = repository;
    }

    public Curso criarCurso(Curso curso) {
        return repository.save(curso);
    }

    public List<Curso> listarCursos(String name, String category) {
        if (name == null && category == null) {
            return repository.findAll();
        }
        return repository.findByNameContainingIgnoreCaseAndCategoryContainingIgnoreCase(
                name == null ? "" : name,
                category == null ? "" : category
        );
    }

    public Curso atualizarCurso(Long id, String name, String category) {
        Curso curso = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado."));
        if (name != null) curso.setName(name);
        if (category != null) curso.setCategory(category);
        return repository.save(curso);
    }

    public void removerCurso(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Curso não encontrado.");
        }
        repository.deleteById(id);
    }

    public Curso toggleActive(Long id) {
        Curso curso = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado."));
        curso.setActive(!curso.isActive());
        return repository.save(curso);
    }
}