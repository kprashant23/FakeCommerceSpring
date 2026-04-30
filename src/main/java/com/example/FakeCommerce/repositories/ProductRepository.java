package com.example.FakeCommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.FakeCommerce.schema.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  List<Product> findByCategory(String category);

  @Query("select distinct p.category from Product p")
  List<String> findAllUniqueCategory();

  // @Query(nativeQuery = true, value = "SELECT p.*, c.name FROM products p INNER
  // JOIN category c on p.category_id = c.id where p.id=:id")
  @Query("SELECT p FROM Product p JOIN FETCH p.category WHERE p.id = :id")
  List<Product> findProductWithDetailsById(@Param("id") Long id);
}