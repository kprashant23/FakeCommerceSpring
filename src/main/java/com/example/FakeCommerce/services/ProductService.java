package com.example.FakeCommerce.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.FakeCommerce.dtos.CreateProductRequestDTO;
import com.example.FakeCommerce.repositories.ProductRepository;
import com.example.FakeCommerce.schema.Product;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product createProduct(CreateProductRequestDTO requestDto) {
        Product newProduct = Product.builder().title(requestDto.getTitle()).description(requestDto.getDescription())
                .image(requestDto.getImage())
                .price(requestDto.getPrice())
                .rating(requestDto.getRating())
                .category(requestDto.getCategory())
                .build();

        return productRepository.save(newProduct); // this will save the product in the database
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getByProductCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<String> getAllUniqueCategory() {
        return productRepository.findAllUniqueCategory();
    }

}