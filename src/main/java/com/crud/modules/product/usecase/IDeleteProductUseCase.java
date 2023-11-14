package com.crud.modules.product.usecase;

import com.crud.modules.product.DTO.ProductRequest;
import com.crud.modules.product.DTO.ProductResponse;
import com.crud.modules.product.entity.Product;

import java.util.List;

public interface IDeleteProductUseCase{
  void delete(String sku);
}
