package com.salesianos.dam.primerejemplo.service;

import com.salesianos.dam.primerejemplo.dto.EditCategoryDto;
import com.salesianos.dam.primerejemplo.model.Category;
import com.salesianos.dam.primerejemplo.repo.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category getById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No existe la categoría con id " + id));
    }

    public Category add(EditCategoryDto dto) {
        return categoryRepository.save(
                Category.builder()
                        .name(dto.name())
                        .build());
    }

    public Category edit(Long id, EditCategoryDto dto) {
        Category category = getById(id);
        category.setName(dto.name());
        return categoryRepository.save(category);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}