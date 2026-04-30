package com.example.FakeCommerce.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.FakeCommerce.dtos.CreateProductRequestDTO;
import com.example.FakeCommerce.dtos.GetProductResponseDTO;
import com.example.FakeCommerce.dtos.GetProductWithDetailsResponseDTO;
import com.example.FakeCommerce.repositories.ProductRepository;
import com.example.FakeCommerce.schema.Category;
import com.example.FakeCommerce.schema.Product;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public List<GetProductResponseDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();

        // List<GetProductResponseDTO> responseDTOs = new ArrayList<>();
        // for(Product product : products){
        // GetProductResponseDTO responseDTO = GetProductResponseDTO.builder()
        // .id(product.getId())
        // .title(product.getTitle())
        // .description(product.getDescription())
        // .price(product.getPrice())
        // .image(product.getImage())
        // .rating(product.getRating())
        // .build();

        // responseDTOs.add(responseDTO);
        // }
        // return responseDTOs;
        return products.stream()
                .map(product -> GetProductResponseDTO.builder()
                        .id(product.getId())
                        .title(product.getTitle())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .image(product.getImage())
                        .rating(product.getRating())
                        .build())
                .collect(Collectors.toList());
    }

    public GetProductResponseDTO getProductById(Long id) {
        return productRepository.findById(id)
                .map(product -> GetProductResponseDTO.builder()
                        .id(product.getId())
                        .title(product.getTitle())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .image(product.getImage())
                        .rating(product.getRating())
                        .build())
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public GetProductWithDetailsResponseDTO getProductWithDetails(Long id) {
        Product product = productRepository.findProductWithDetailsById(id).get(0);

        return GetProductWithDetailsResponseDTO.builder()
                .id(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory().getName())
                .image(product.getImage())
                .rating(product.getRating())
                .build();
    }

    public Product createProduct(CreateProductRequestDTO requestDto) {
        Category category = categoryService.getCategoryById(requestDto.getCategoryId());
        Product newProduct = Product.builder().title(requestDto.getTitle()).description(requestDto.getDescription())
                .image(requestDto.getImage())
                .price(requestDto.getPrice())
                .rating(requestDto.getRating())
                .category(category)
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