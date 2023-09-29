package com.crud.repository;

import com.crud.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {
  @Query(value = "SELECT * FROM products WHERE id = :id", nativeQuery = true)
  Product findProductById(Integer id);

  Product deleteProductById(Integer id);
}
