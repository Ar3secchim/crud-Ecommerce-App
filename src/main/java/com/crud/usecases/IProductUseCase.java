package com.crud.usecases;

import com.crud.model.Product;

import java.util.List;

public interface IProductUseCase {
  Product create(Product product);

  List<Product> listAll();

  List<Product> findByDescription(String description);

  Product findByBarcode(String barcode);

}
