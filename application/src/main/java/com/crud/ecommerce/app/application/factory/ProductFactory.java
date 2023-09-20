package com.crud.ecommerce.app.application.factory;

import com.crud.ecommerce.app.application.IProductUseCase;
import com.crud.ecommerce.app.application.impl.ProductUseCaseImpl;
import com.crud.ecommerce.app.application.integration.database.MemoryDatabase;
import com.crud.ecommerce.app.application.integration.memoryrepository.ProductMemoryRepositoryImpl;
import com.crud.ecommerce.app.application.repository.ProductRepository;


public class ProductFactory {
  public static IProductUseCase createUseCase() {
    return new ProductUseCaseImpl(
            createRepository()
    );
  }

  public static ProductRepository createRepository() {
    return new ProductMemoryRepositoryImpl(
            MemoryDatabase.getInstance()
    );
  }
}
