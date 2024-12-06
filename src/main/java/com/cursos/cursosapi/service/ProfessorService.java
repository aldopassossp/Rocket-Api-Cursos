package com.cursos.cursosapi.service;

import com.cursos.cursosapi.model.Professor;
import com.cursos.cursosapi.repository.ProfessorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository repository;

    public Professor criarProfessor(Professor professor) {
        return repository.save(professor);
    }

    public List<Professor> listarProfessores() {

            return repository.findAll();
    }

    public Optional<Professor> listarPorId(Long id) {

        return repository.findById(id);
    }

    public Professor atualizarProfessor(Long id, String name, String email) {
        Professor professor = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Professor não encontrado."));
        if (name != null) professor.setName(name);
        if (email != null) professor.setEmail(email);
        return repository.save(professor);
    }

    public void removerProfessor(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Professor não encontrado.");
        }
        repository.deleteById(id);
    }

}
