package com.salesianos.dam.primerejemplo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductsController {


    private final ProductRepository productRepository;

    public ProductsController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String name) {
        // Enfoque idempotente:
        // productRepository.deleteProduct(name);
        // return ResponseEntity.noContent().build();

        // Enfoque no idempotente (404 si no existe):
        if (productRepository.getProductByName(name).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        productRepository.deleteProduct(name);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return ResponseEntity.status(201)
                .body(productRepository.addProduct(product));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> result = productRepository.getProducts();
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
