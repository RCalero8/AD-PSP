package com.salesianos.dam.primerejemplo.controller;

import com.salesianos.dam.primerejemplo.dto.EditCategoryDto;
import com.salesianos.dam.primerejemplo.dto.GetCategoryDTO;
import com.salesianos.dam.primerejemplo.model.Category;
import com.salesianos.dam.primerejemplo.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<GetCategoryDTO> getAll() {
        return categoryService.getAll().stream()
                .map(GetCategoryDTO::of)
                .toList();
    }

    @GetMapping("/{id}")
    public GetCategoryDTO getById(@PathVariable Long id) {
        return GetCategoryDTO.of(categoryService.getById(id));
    }

    @PostMapping
    public ResponseEntity<GetCategoryDTO> create(@Valid @RequestBody EditCategoryDto dto) {
        Category category = new Category();
        category.setName(dto.name());

        Category saved = categoryService.addCategory(category);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(location).body(GetCategoryDTO.of(saved));
    }

    @PutMapping("/{id}")
    public GetCategoryDTO update(@PathVariable Long id,
                                 @Valid @RequestBody EditCategoryDto dto) {
        Category category = new Category();
        category.setName(dto.name());

        return GetCategoryDTO.of(categoryService.updateCategory(id, category));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}