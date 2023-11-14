package com.crud.modules.product.usecase;

import com.crud.modules.product.DTO.ProductRequest;
import com.crud.modules.product.DTO.ProductResponse;

public interface IUpdateProductUseCase {
  ProductResponse update(Integer id, ProductRequest productRequest);
}
