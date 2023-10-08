package com.crud.service;

import com.crud.controller.dto.product.ProductRequest;
import com.crud.controller.dto.product.ProductResponse;
import com.crud.model.Product;
import com.crud.usecases.IProductUseCase;
import com.crud.repository.ProductRepository;
import com.crud.utils.ProductConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductUseCase {
  @Autowired
  ProductRepository repository;

  public ProductResponse create(Product product) {
    Product productResponse = repository.save(product);
    return ProductConvert.toResponse(productResponse);
  }

  public List<ProductResponse> listAll() {
    return ProductConvert.toListResponse(repository.findAll());
  }

  public ProductResponse findById(Integer id) {
    Product found = null;

    if (id != null) {
      found = repository.findProductById(id);
    }
    return ProductConvert.toResponse(found);
  }

  public void delete(Integer id) {
    Product product = repository.findProductById(id);
    if (id != null) {
      repository.delete(product);
    }
  }

  @Override
  public ProductResponse updateProduct(Integer id, ProductRequest productRequest) {
    Product product = ProductConvert.toEntity(productRequest);
    product.setId(id);
    return ProductConvert.toResponse(repository.save(product));
  }
}
