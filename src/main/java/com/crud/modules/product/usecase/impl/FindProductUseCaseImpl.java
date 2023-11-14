package com.crud.modules.product.usecase.impl;

import com.crud.modules.product.DTO.ProductResponse;
import com.crud.modules.product.entity.Product;
import com.crud.modules.product.repository.ProductRepository;
import com.crud.modules.product.usecase.IFindProductUseCase;
import com.crud.utils.ProductConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindProductUseCaseImpl implements IFindProductUseCase {
  @Autowired
  ProductRepository repository;

  @Override
  public List<ProductResponse> listAll() {
    return ProductConvert.toListResponse(repository.findAll());
  }

  @Override
  public ProductResponse findById(String id) {
    Product product = repository.findProductById(id);

    return ProductConvert.toResponse(product);
  }
}
