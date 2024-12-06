package com.cursos.cursosapi.controller;

import com.cursos.cursosapi.model.Professor;
import com.cursos.cursosapi.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService service;

    @PostMapping
    public ResponseEntity<Professor> criarProfessor(@Valid @RequestBody Professor professor) {
        return ResponseEntity.ok(service.criarProfessor(professor));
    }

    @GetMapping
    public ResponseEntity<List<Professor>> listarProfessores() {
        return ResponseEntity.ok(service.listarProfessores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Professor>> listarPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(service.listarPorId(id));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Professor> atualizarProfessor(
            @PathVariable Long id,
            @RequestBody Professor professor) {
        return ResponseEntity.ok(service.atualizarProfessor(id, professor.getName(), professor.getEmail()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerCurso(@PathVariable Long id) {
        service.removerProfessor(id);
        return ResponseEntity.noContent().build();
    }

}
