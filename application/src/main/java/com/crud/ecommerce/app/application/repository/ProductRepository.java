package com.crud.ecommerce.app.application.repository;

import com.crud.ecommerce.app.domain.Product;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

  List<Product> findByDescription(String description);

  Product findByBarcode(String barcode);
}
