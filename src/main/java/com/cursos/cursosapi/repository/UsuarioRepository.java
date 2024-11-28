package com.cursos.cursosapi.repository;

import com.cursos.cursosapi.model.Usuario;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

     UserDetails findByLogin(String login);

     Optional<Object> findByLoginOrEmail(@NotBlank(message = "O usuário é obrigatório.") String username, String email);
}
