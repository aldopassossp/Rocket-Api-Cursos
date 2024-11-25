package com.cursos.cursosapi.service;

import com.cursos.cursosapi.model.Curso;
import com.cursos.cursosapi.model.Usuario;
import com.cursos.cursosapi.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario criarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsurios(){
        return usuarioRepository.findAll();
    }

    public Usuario atualizarUsuario(Long id, String nome, String username, String password, String email){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado."));
        if (nome != null) usuario.setNome(nome);
        if (username != null) usuario.setUsername(username);
        if (password != null) usuario.setPassword(password);
        if (email != null) usuario.setEmail(email);
        return usuarioRepository.save(usuario);
    }

    public void removerUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuario não encontrado.");
        }
        usuarioRepository.deleteById(id);
    }

    public Usuario toggleActive(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado."));
        usuario.setActive(!usuario.isActive());
        return usuarioRepository.save(usuario);
    }
}
