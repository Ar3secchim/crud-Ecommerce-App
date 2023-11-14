package com.crud.modules.product.usecase;

import com.crud.modules.product.DTO.ProductResponse;
import com.crud.modules.product.entity.Product;

public interface ICreateProductUseCase {
  ProductResponse create(Product product);
}
