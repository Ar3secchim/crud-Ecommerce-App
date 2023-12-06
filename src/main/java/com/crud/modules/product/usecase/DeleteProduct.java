package com.crud.modules.product.usecase;

import com.crud.modules.product.entity.Product;
import com.crud.modules.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class DeleteProduct {
  @Autowired
  ProductRepository repository;

  public void execute(String id) throws Exception {
    Product product = repository.findProductById(id);

    if (product == null){
      throw new Exception("Produto não encontrado");
    }

    repository.delete(product);
  }
}
