package com.example.FakeCommerce.dtos;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductRequestDTO {
  private String title;

  private String description;

  private BigDecimal price;

  private String image;

  private Long categoryId;

  private BigDecimal rating;

}
