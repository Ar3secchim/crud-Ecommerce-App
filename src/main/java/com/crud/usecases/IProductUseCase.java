package com.crud.usecases;

import com.crud.controller.dto.product.ProductResponse;
import com.crud.model.Product;

import java.util.List;

public interface IProductUseCase {
  ProductResponse create(Product product);
  List<ProductResponse> listAll();
  ProductResponse findById(Integer id);
  Product findByName(String name);
  void update(Product product);
}
