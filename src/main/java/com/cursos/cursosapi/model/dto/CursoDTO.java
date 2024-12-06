package com.cursos.cursosapi.model.dto;

import com.cursos.cursosapi.model.Curso;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CursoDTO {
    private Long id;
    private String name;
    private String category;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long professorId; // ID do professor
    private String professorName; // Nome do professor
    private String professorEmail;

    public CursoDTO(Curso curso) {
        this.id = curso.getId();
        this.name = curso.getName();
        this.category = curso.getCategory();
        this.active = curso.isActive();
        this.createdAt = curso.getCreatedAt();
        this.updatedAt = curso.getUpdatedAt();

        if (curso.getProfessor() != null) {
            this.professorId = curso.getProfessor().getId();
            this.professorName = curso.getProfessor().getName();
            this.professorEmail = curso.getProfessor().getEmail();
        }
    }
}
