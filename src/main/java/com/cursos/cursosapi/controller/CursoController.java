package com.cursos.cursosapi.controller;

import java.util.List;
import java.util.Optional;

import com.cursos.cursosapi.model.Curso;
import com.cursos.cursosapi.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService service;

    public CursoController(CursoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Curso> criarCurso(@Valid @RequestBody Curso curso) {
        return ResponseEntity.ok(service.criarCurso(curso));
    }

    @GetMapping
    public ResponseEntity<List<Curso>> listarCursos(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category) {
        return ResponseEntity.ok(service.listarCursos(name, category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Curso>> listarPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(service.listarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizarCurso(
            @PathVariable Long id,
            @RequestBody Curso curso) {
        return ResponseEntity.ok(service.atualizarCurso(id, curso.getName(), curso.getCategory()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerCurso(@PathVariable Long id) {
        service.removerCurso(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<Curso> toggleActive(@PathVariable Long id) {
        return ResponseEntity.ok(service.toggleActive(id));
    }
}