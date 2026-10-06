package com.salesianos.dam.primerejemplo.util;

import com.salesianos.dam.primerejemplo.dto.EditCategoryDto;
import com.salesianos.dam.primerejemplo.dto.EditProductDto;
import com.salesianos.dam.primerejemplo.service.CategoryService;
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

        // Categorías de ejemplo
        if (categoryService.getAll().isEmpty()) {
            List.of("Electrónica", "Hogar", "Libros", "Deportes")
                    .forEach(name -> categoryService.add(new EditCategoryDto(name)));
        }

        // Productos de ejemplo
        // (EditProductDto: name, price, details)
        productService.addProduct(new EditProductDto("Portátil 15\"", 749.99, "Intel i5, 16 GB RAM, 512 GB SSD"));
        productService.addProduct(new EditProductDto("Auriculares Bluetooth", 59.90, "Cancelación de ruido"));
        productService.addProduct(new EditProductDto("Lámpara de mesa", 24.50, "LED regulable"));
        productService.addProduct(new EditProductDto("Novela de ciencia ficción", 14.95, "Tapa blanda, 380 páginas"));
        productService.addProduct(new EditProductDto("Balón de fútbol", 19.99, "Talla 5"));
    }

}