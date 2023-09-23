package com.crud.usecases.impl;

import com.crud.model.Product;
import com.crud.repository.ProductRepository;
import com.crud.usecases.IProductUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductUseCaseImpl implements IProductUseCase {
  @Autowired
  ProductRepository repository;

  @Override
  public Product create(Product product) {
    return repository.save(product);
  }

  @Override
  public List<Product> listAll() {
    return repository.findAll();
  }

  @Override
  public List<Product> findByDescription(String description) {
    List<Product> found = new ArrayList<>();
    if (description != null) {
      found = repository.findByDescription(description);
    }
    return found;
  }

  @Override
  public Product findByBarcode(String barcode) {
    Product found = null;
    if (barcode != null) {
      found = repository.findByBarcode(barcode);
    }
    return found;
  }
}
