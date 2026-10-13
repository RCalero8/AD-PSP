package com.salesianos.dam.primerejemplo.service;

import com.salesianos.dam.primerejemplo.error.InvalidProductException;
import com.salesianos.dam.primerejemplo.error.notfound.CategoryNotFoundException;
import com.salesianos.dam.primerejemplo.model.Category;
import com.salesianos.dam.primerejemplo.repo.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAll(){
        List<Category> categories = categoryRepository.findAll();
        if  (categories.isEmpty()) {
            throw new CategoryNotFoundException();
        }

        return categories;
    }

    public Category getById(Long id){
        return categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);
    }

    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category category){
        if (!categoryRepository.existsById(id)) {
            throw new CategoryNotFoundException();
        }
        category.setName(category.getName());
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }

}

