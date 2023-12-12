package com.crud.modules.product.repository;

import com.crud.modules.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, String> {
  @Query(value = "SELECT * FROM products WHERE sku_id = :id", nativeQuery = true)
  Product findProductById(String id);
}
