package com.crud.modules.product.usecase;

import com.crud.modules.product.DTO.ProductResponse;
import com.crud.modules.product.entity.Product;
import com.crud.modules.product.repository.ProductRepository;
import com.crud.utils.ProductConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindProduct {
  @Autowired
  ProductRepository repository;

  public List<ProductResponse> listAll() {
    return ProductConvert.toListResponse(repository.findAll());
  }

  public ProductResponse findById(String id) {
    Product product = repository.findProductById(id);

    return ProductConvert.toResponse(product);
  }
}
