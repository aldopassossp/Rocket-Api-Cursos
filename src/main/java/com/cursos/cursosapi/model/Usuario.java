package com.cursos.cursosapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(nullable = false)
    @NotBlank(message = "O usuário é obrigatório.")
    private String username;

    @Column(nullable = false)
    @NotBlank(message = "O password obrigatório.")
    private String password;

    private String email;

    @Column(nullable = false)
    private boolean active = true;
}
