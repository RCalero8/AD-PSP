package com.salesianos.dam.primerejemplo.repo;
import com.salesianos.dam.primerejemplo.model.Product;


import org.springframework.data.jpa.repository.JpaRepository;





public interface ProductRepository
        extends JpaRepository<Product, Long> {}
