package usecases.impl;

import model.Product;
import usecases.IProductUseCase;
import usecases.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductUseCaseImpl implements IProductUseCase {

  private final ProductRepository repository;

  public ProductUseCaseImpl(ProductRepository repository) {
    this.repository = repository;
  }

  @Override
  public void create(Product product) {
    repository.save(product);
  }

  @Override
  public List<Product> listAll() {
    return repository.listAll();
  }

  @Override
  public List<Product> findByDescription(String description) {
    List<Product> found = new ArrayList<>();
    if (description != null) {
      found = repository.findByDescription(description);
    }
    return found;
  }

  @Override
  public Product findByBarcode(String barcode) {
    Product found = null;
    if (barcode != null) {
      found = repository.findByBarcode(barcode);
    }
    return found;
  }

  @Override
  public void update(Product product) {
    repository.update(product);
  }

  @Override
  public Product delete(Long id) {
    Product product = repository.findById(id);
    if (product != null) {
      repository.delete(product);
    }
    return product;
  }

}
