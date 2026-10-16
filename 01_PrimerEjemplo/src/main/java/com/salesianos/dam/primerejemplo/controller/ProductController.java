package com.salesianos.dam.primerejemplo.controller;

import com.salesianos.dam.primerejemplo.dto.EditProductDto;
import com.salesianos.dam.primerejemplo.dto.GetProductDetail;
import com.salesianos.dam.primerejemplo.dto.GetProductList;
import com.salesianos.dam.primerejemplo.model.Product;
import com.salesianos.dam.primerejemplo.repo.ProductRepository;
import com.salesianos.dam.primerejemplo.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<GetProductDetail> addProduct(
            @Valid @RequestBody EditProductDto product) {

        return ResponseEntity.status(201)
                .body(GetProductDetail.of(productService.addProduct(product)));

    }

    @GetMapping
    public ResponseEntity<List<GetProductList>> getAllProducts() {

        List<Product> result = productService.getAllProducts();
        return ResponseEntity.ok(
                result
                        .stream()
                        .map(GetProductList::of)
                        .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetProductDetail> getProductById(@PathVariable Long id) {

        return ResponseEntity.ok(GetProductDetail.of(productService.getProductById(id)));

    }

    @PutMapping("/{id}")
    public ResponseEntity<GetProductDetail> updateProduct(
            @PathVariable Long id,
            @RequestBody EditProductDto product) {

        return ResponseEntity.ok(
                GetProductDetail.of(productService.updateProduct(id, product)));


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}