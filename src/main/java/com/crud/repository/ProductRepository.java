package com.crud.repository;

import com.crud.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {

  @Query(value = "SELECT * FROM products WHERE :productBarcode", nativeQuery = true)
  Product findByBarcode(String productBarcode);

  @Query(value = "SELECT * FROM products WHERE :id", nativeQuery = true)
  Product findAllById(Integer id);

  Product deleteProductById(Integer id);

}
