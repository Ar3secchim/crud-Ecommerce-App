package com.crud.repository;

import com.crud.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
  List<Product> findByDescription(String description);

  Product findByBarcode(String barcode);
}
