package com.crud.modules.product.usecase.impl;

import com.crud.modules.product.DTO.ProductRequest;
import com.crud.modules.product.DTO.ProductResponse;
import com.crud.modules.product.entity.Product;
import com.crud.modules.product.repository.ProductRepository;
import com.crud.modules.product.usecase.IUpdateProductUseCase;
import com.crud.utils.ProductConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductUseCaseImpl implements IUpdateProductUseCase {
  @Autowired
  ProductRepository repository;

  @Override
  public ProductResponse update(Integer id, ProductRequest productRequest) {
    Product product = ProductConvert.toEntity(productRequest);
    product.setId(id);
    return ProductConvert.toResponse(repository.save(product));
  }
}
