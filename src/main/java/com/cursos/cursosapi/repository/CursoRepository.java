package com.cursos.cursosapi.repository;

import com.cursos.cursosapi.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    List<Curso> findByNameContainingIgnoreCaseAndCategoryContainingIgnoreCase(String name, String category);
}