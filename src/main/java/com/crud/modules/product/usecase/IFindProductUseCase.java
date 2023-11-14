package com.crud.modules.product.usecase;

import com.crud.modules.product.DTO.ProductResponse;

import java.util.List;

public interface IFindProductUseCase {
  List<ProductResponse> listAll();

  ProductResponse findById(String id);
}
