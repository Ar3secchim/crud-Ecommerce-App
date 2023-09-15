package factory;

import integration.database.MemoryDatabase;
import integration.memoryrepository.ProductMemoryRepositoryImpl;
import usecases.IProductUseCase;
import usecases.impl.ProductUseCaseImpl;
import usecases.repository.ProductRepository;

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
