package com.cursos.cursosapi.service;

import java.util.List;

import com.cursos.cursosapi.model.Curso;
import com.cursos.cursosapi.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> listarTodos() {
        return cursoRepository.findAll();
    }

    public Curso salvar(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso atualizar(Long id, Curso curso) {
        if (cursoRepository.existsById(id)) {
            curso.setId(id);
            return cursoRepository.save(curso);
        }
        return null;
    }

    public void deletar(Long id) {
        cursoRepository.deleteById(id);
    }

    public Curso buscarPorId(Long id) {
        return cursoRepository.findById(id).orElse(null);
    }
}

