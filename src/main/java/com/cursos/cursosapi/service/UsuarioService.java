package com.cursos.cursosapi.service;

import com.cursos.cursosapi.exceptions.UserFoundException;
import com.cursos.cursosapi.model.Curso;
import com.cursos.cursosapi.model.Usuario;
import com.cursos.cursosapi.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario criarUsuario(Usuario usuario){
        this.usuarioRepository.findByLoginOrEmail(usuario.getUsername(), usuario.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });

        var password = passwordEncoder.encode(usuario.getPassword());
        usuario.setSenha(password);

        return this.usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsurios(){
        return usuarioRepository.findAll();
    }

    public Usuario atualizarUsuario(Long id, String login, String password, String email){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado."));
        if (login != null) usuario.setLogin(login);
        if (password != null) usuario.setSenha(password);
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
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado."));
        usuario.isEnabled();
        return usuarioRepository.save(usuario);
    }
}
