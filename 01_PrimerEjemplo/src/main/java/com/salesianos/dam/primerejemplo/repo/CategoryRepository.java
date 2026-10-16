package com.salesianos.dam.primerejemplo.repo;

import com.salesianos.dam.primerejemplo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}