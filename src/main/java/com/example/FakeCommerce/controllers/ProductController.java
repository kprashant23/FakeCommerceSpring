package com.example.FakeCommerce.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.FakeCommerce.dtos.CreateProductRequestDTO;
import com.example.FakeCommerce.dtos.GetProductResponseDTO;
import com.example.FakeCommerce.dtos.GetProductWithDetailsResponseDTO;
import com.example.FakeCommerce.schema.Product;
import com.example.FakeCommerce.services.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
  private final ProductService productService;

  @GetMapping
  public List<GetProductResponseDTO> getAllProducts() {
    return productService.getAllProducts();
  }

  @PostMapping
  public Product createProduct(@RequestBody CreateProductRequestDTO requestDto) {
    return productService.createProduct(requestDto);
  }

  @GetMapping("/{id}/details")
  public GetProductWithDetailsResponseDTO getProductWithDetails(@PathVariable Long id) {
    return productService.getProductWithDetails(id);
  }

  @DeleteMapping("/{id}")
  public void deleteProduct(@PathVariable Long id) {
    productService.deleteProduct(id);
  }

  @GetMapping("/search")
  public List<Product> getProductByCategory(@RequestParam("categoryName") String category) {
    return productService.getByProductCategory(category);
  }

  // Write an API to get all unique category
  @GetMapping("/categories")
  public List<String> getAllUniqueCategory() {
    return productService.getAllUniqueCategory();
  }
}