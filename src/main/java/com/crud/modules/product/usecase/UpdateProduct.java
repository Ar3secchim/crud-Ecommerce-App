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
    Product product = repository.findProductById(id);

    if(product == null){
      throw new Exception("Not exist product");
    }

    product.setDescription(productRequest.getDescription());
    product.setQuantityStock(productRequest.getQuantityStock());
    product.setName(productRequest.getName());
    product.setSkuId(id);
    repository.save(product);

    return ProductConvert.toResponse(product);
  }
}
