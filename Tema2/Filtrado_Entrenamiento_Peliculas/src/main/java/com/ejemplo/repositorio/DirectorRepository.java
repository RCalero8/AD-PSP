package com.ejemplo.repositorio;

import com.ejemplo.modelo.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
public interface DirectorRepository extends JpaRepository<Director, Long> {}
