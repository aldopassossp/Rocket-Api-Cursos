package com.cursos.cursosapi.controller;

import com.cursos.cursosapi.model.Usuario;
import com.cursos.cursosapi.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Object> criarUsuario(@Valid @RequestBody Usuario usuario){
        try {
            var result = this.usuarioService.criarUsuario(usuario);
            return ResponseEntity.ok().body(result);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) String email) {
        return ResponseEntity.ok(usuarioService.listarUsurios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> listarPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.listarPorId(id));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(
            @PathVariable Long id,
            @RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.atualizarUsuario(id, usuario.getLogin(), usuario.getSenha(), usuario.getEmail()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerCurso(@PathVariable Long id) {
        usuarioService.removerUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<Usuario> toggleActive(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.toggleActive(id));
    }


}
