package com.ejemplo.repositorio;

import com.ejemplo.modelo.Productora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
public interface ProductoraRepository extends JpaRepository<Productora, Long> {}
