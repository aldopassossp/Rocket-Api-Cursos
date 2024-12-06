package com.cursos.cursosapi.repository;

import com.cursos.cursosapi.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
