package com.crud.modules.product.usecase.impl;

import com.crud.modules.product.DTO.ProductRequest;
import com.crud.modules.product.DTO.ProductResponse;
import com.crud.modules.product.entity.Product;
import com.crud.modules.product.repository.ProductRepository;
import com.crud.modules.product.usecase.IDeleteProductUseCase;
import com.crud.utils.ProductConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeleteProductUseCaseImpl implements IDeleteProductUseCase {
  @Autowired
  ProductRepository repository;

  public void delete(String id) {
    Product product = repository.findProductById(id);

    if (product.getSku() == null){
      throw new RuntimeException("Produto não encontrado");
    }

    repository.delete(product);
  }
}
