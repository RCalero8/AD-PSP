package com.salesianos.dam.primerejemplo.utils;

import com.salesianos.dam.primerejemplo.model.Product;
import com.salesianos.dam.primerejemplo.service.ProductService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeed {

    private final CategoryService categoryService;
    private final ProductService productService;

    @PostConstruct
    public void initData() {

        // Categorías
        Category electronica = categoryService.addCategory(newCategory("Electrónica"));
        Category hogar = categoryService.addCategory(newCategory("Hogar"));
        Category deportes = categoryService.addCategory(newCategory("Deportes"));

        // Productos (ajustar a los campos reales de Product)
        List<Product> productos = List.of(
                newProduct("Portátil", 899.99, electronica),
                newProduct("Auriculares", 49.90, electronica),
                newProduct("Lámpara de mesa", 25.50, hogar),
                newProduct("Cafetera", 59.00, hogar),
                newProduct("Balón de fútbol", 19.95, deportes)
        );

        productos.forEach(productService::addProduct);
    }

    private Category newCategory(String name) {
        Category c = new Category();
        c.setName(name);
        return c;
    }

    private Product newProduct(String name, double price, Category category) {
        Product p = new Product();
        p.setName(name);
        p.setPrice(price);
        p.setCategory(category);
        return p;
    }
}