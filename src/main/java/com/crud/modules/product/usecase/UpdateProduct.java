package com.crud.modules.product.usecase;

import com.crud.modules.product.DTO.ProductRequest;
import com.crud.modules.product.DTO.ProductResponse;
import com.crud.modules.product.entity.Product;
import com.crud.modules.product.repository.ProductRepository;
import com.crud.utils.ProductConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateProduct {
  @Autowired
  ProductRepository repository;

  public ProductResponse execute(String id, ProductRequest productRequest) throws Exception {
    if(repository.findProductById(id) == null){
      throw new Exception("Not exist product");
    }

    Product product = ProductConvert.toEntity(productRequest);
    product.setSku(id);
    repository.save(product);
    return ProductConvert.toResponse(product);
  }
}
