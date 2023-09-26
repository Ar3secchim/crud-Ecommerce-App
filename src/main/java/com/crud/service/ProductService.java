package com.crud.service;

import com.crud.model.Product;
import com.crud.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
  @Autowired
  ProductRepository repository;


  public Product create(Product product) {
    return repository.save(product);
  }


  public List<Product> listAll() {
    return repository.findAll();
  }


  public List<Product> findByDescription(String description) {
    List<Product> found = new ArrayList<>();
    if (description != null) {
      found = repository.findByDescription(description);
    }
    return found;
  }


  public Product findByBarcode(String barcode) {
    Product found = null;
    if (barcode != null) {
      found = repository.findByBarcode(barcode);
    }
    return found;
  }


  public Product deleteProductById(Integer id) {
    Product found = null;
    if (id != null) {
      found = repository.deleteProductById(id);
    }
    return found;
  }

}
