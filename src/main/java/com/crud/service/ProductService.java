package com.crud.service;

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

  //TODO implementar FindByName product
  @Override
  public Product findByName(String name) {
    return null;
  }

  //TODO implementar update product
  @Override
  public void update(Product product) {
  }

  public void delete(Integer id) {
    if (id != null) {
     repository.deleteProductById(id);
    }
  }
}
