package com.example.FakeCommerce.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.FakeCommerce.dtos.CreateCategoryDTO;
import com.example.FakeCommerce.repositories.CategoryRepository;
import com.example.FakeCommerce.schema.Category;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
  private final CategoryRepository categoryRepository;

  public Category createCategory(CreateCategoryDTO requestDto) {
    Category newCategory = Category.builder().name(requestDto.getName()).build();
    return categoryRepository.save(newCategory);
  }

  public List<Category> getAllCategory() {
    return categoryRepository.findAll();
  }

  public Category getCategoryById(Long id) {
    return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
  }

  public void deleteCategory(Long id) {
    categoryRepository.deleteById(id);
  }
}
