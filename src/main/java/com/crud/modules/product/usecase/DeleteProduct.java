package com.crud.modules.product.usecase;

import com.crud.modules.product.entity.Product;
import com.crud.modules.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteProduct {
  @Autowired
  ProductRepository repository;

  public void execute(String id) {
    Product product = repository.findProductById(id);

    if (product.getSku() == null){
      throw new RuntimeException("Produto não encontrado");
    }

    repository.delete(product);
  }
}
