package com.ejemplo.repositorio;

import com.ejemplo.modelo.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
public interface InstructorRepository extends JpaRepository<Instructor, Long> {}
