package com.ejemplo.repositorio;

import com.ejemplo.modelo.Tematica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
public interface TematicaRepository extends JpaRepository<Tematica, Long> {}
