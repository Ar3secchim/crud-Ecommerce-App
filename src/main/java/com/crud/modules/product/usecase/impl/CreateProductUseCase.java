package com.crud.modules.product.usecase.impl;

import com.crud.modules.product.DTO.ProductResponse;
import com.crud.modules.product.entity.Product;
import com.crud.modules.product.repository.ProductRepository;
import com.crud.modules.product.usecase.ICreateProductUseCase;
import com.crud.utils.ProductConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateProductUseCase implements ICreateProductUseCase {
  @Autowired
  ProductRepository repository;
  @Override
  public ProductResponse create(Product product) {
    Product productResponse = repository.save(product);
    return ProductConvert.toResponse(productResponse);
  }
}
