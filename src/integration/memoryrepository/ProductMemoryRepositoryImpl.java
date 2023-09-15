package integration.memoryrepository;

import integration.database.MemoryDatabase;
import model.Product;
import usecases.repository.ProductRepository;
import usecases.repository.RepositoryException;

import java.util.List;
import java.util.Objects;

public class ProductMemoryRepositoryImpl implements ProductRepository {

  private final MemoryDatabase database;

  public ProductMemoryRepositoryImpl(MemoryDatabase database) {
    this.database = database;
  }

  @Override
  public void save(Product product) throws RepositoryException {
    product.setId(database.nextId());
    database.saveOrUpdate(product);
  }

  @Override
  public List<Product> listAll() throws RepositoryException {
    return database.listAll(Product.class);
  }

  @Override
  public Product findById(Long id) throws RepositoryException {
    return database.find(
            Product.class,
            it -> Objects.equals(id, it.getId())
    ).stream().findFirst().orElse(null);
  }

  @Override
  public void update(Product product) throws RepositoryException {
    Product inserted = findById(product.getId());
    inserted.setDescription(product.getDescription());
    inserted.setBarcode(product.getBarcode());
    inserted.setPrice(product.getPrice());
    database.saveOrUpdate(inserted);
  }

  @Override
  public Product delete(Product product) throws RepositoryException {
    return database.delete(product);
  }

  @Override
  public List<Product> findByDescription(String description) {
    return database.find(
            Product.class,
            it -> Objects.equals(description, it.getDescription())
    );
  }

  @Override
  public Product findByBarcode(String barcode) {
    return database.find(
            Product.class,
            it -> Objects.equals(barcode, it.getBarcode())
    ).stream().findFirst().orElse(null);
  }

}
