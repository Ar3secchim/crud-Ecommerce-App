package com.crud.service;

import com.crud.controller.dto.product.ProductResponse;
import com.crud.model.Product;
import com.crud.repository.ProductRepository;
import com.crud.utils.ProductConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
  @Autowired
  ProductRepository repository;

  public Product create(Product product) {
    return repository.save(product);
  }

  public List<ProductResponse> listAll() {
    List<Product> listProduct = repository.findAll();
    return ProductConvert.toListResponse(listProduct);
  }

  public ProductResponse getAllProduct(String productBarcode, Integer productId) {
    ProductResponse found = null;


    if (productBarcode != null) {
      found = findByBarcode(productBarcode);
    }

    if(productId != null){
      found = findById(productId);
    }

    return found;
  }

  private ProductResponse findByBarcode(String productBarcode) {
    Product found = null;
    if (productBarcode != null) {
      found = repository.findByBarcode(productBarcode);
    }
    return ProductConvert.toResponse(found);
  }

  public ProductResponse findById(Integer id) {
    Product found = null;
    if (id != null) {
      found = repository.findAllById(id);
    }
    return ProductConvert.toResponse(found);
  }

  public ProductResponse deleteProductById(Integer id) {
    Product found = null;
    if (id != null) {
      found = repository.deleteProductById(id);
    }
    return ProductConvert.toResponse(found);
  }

}
