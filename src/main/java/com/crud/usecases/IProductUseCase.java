package com.crud.usecases;

import com.crud.controller.dto.product.ProductRequest;
import com.crud.controller.dto.product.ProductResponse;
import com.crud.model.Product;

import java.util.List;

public interface IProductUseCase {
  ProductResponse create(Product product);
  List<ProductResponse> listAll();
  ProductResponse findById(Integer id);
  ProductResponse updateProduct(Integer id, ProductRequest productRequest);
}
