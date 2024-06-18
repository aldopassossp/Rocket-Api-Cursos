package com.cursos.cursosapi.controller;

import java.util.List;

import com.cursos.cursosapi.model.Curso;
import com.cursos.cursosapi.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @GetMapping
    public List<Curso> listarTodos() {
        return cursoService.listarTodos();
    }

    @PostMapping
    public Curso salvar(@RequestBody Curso curso) {
        return cursoService.salvar(curso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizar(@PathVariable Long id, @RequestBody Curso curso) {
        Curso atualizado = cursoService.atualizar(id, curso);
        if (atualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        cursoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscarPorId(@PathVariable Long id) {
        Curso curso = cursoService.buscarPorId(id);
        if (curso == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(curso);
    }
}

