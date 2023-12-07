package com.crud.modules.product.usecase;

import com.crud.modules.product.DTO.ProductRequest;
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

  public ProductResponse execute(ProductRequest productRequest) throws Exception {
    validateProduct(productRequest);

    Product product = ProductConvert.toEntity(productRequest);
    repository.save(product);
    return ProductConvert.toResponse(product);
  }

  private void validateProduct(ProductRequest productRequest) {
    if (productRequest.getName() == null || productRequest.getName().trim().isEmpty()) {
      throw new IllegalArgumentException("Name is required");
    }

    if (productRequest.getQuantityStock() == null) {
      throw new IllegalArgumentException("Quantity is required");
    }

    if (productRequest.getPrice() == null) {
      throw new IllegalArgumentException("Price is required");
    }
  }
}
