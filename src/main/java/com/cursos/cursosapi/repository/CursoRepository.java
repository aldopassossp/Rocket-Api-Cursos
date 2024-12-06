package com.cursos.cursosapi.repository;

import com.cursos.cursosapi.model.Curso;
import com.cursos.cursosapi.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    List<Curso> findByNameContainingIgnoreCaseAndCategoryContainingIgnoreCase(String name, String category);
}