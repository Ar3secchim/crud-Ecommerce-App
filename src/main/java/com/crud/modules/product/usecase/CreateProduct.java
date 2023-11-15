package com.crud.modules.product.usecase;

import com.crud.modules.product.DTO.ProductResponse;
import com.crud.modules.product.entity.Product;
import com.crud.modules.product.repository.ProductRepository;
import com.crud.utils.ProductConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateProduct {
  @Autowired
  ProductRepository repository;

  public ProductResponse execute(Product product) {
    Product productResponse = repository.save(product);
    return ProductConvert.toResponse(productResponse);
  }
}
