package com.crud.modules.product.usecase;

import com.crud.infra.exception.BadRequestClient;
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
    var product = ProductConvert.toEntity(productRequest);
    repository.save(product);
    return ProductConvert.toResponse(product);
  }

  private void validateProduct(ProductRequest productRequest) throws Exception {
    if (productRequest.getName() == null) {
      throw new Exception("Name is required");
    }

    if (productRequest.getQuantityStock() == null) {
      throw new Exception("Quantity is required");
    }

    if (productRequest.getPrice() == null) {
      throw new Exception("Price is required");
    }
  }
}
