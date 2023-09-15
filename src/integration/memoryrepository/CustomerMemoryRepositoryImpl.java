package integration.memoryrepository;

import integration.database.MemoryDatabase;
import model.Customer;
import usecases.repository.CustomerRepository;
import usecases.repository.RepositoryException;

import java.util.List;
import java.util.Objects;

public class CustomerMemoryRepositoryImpl implements CustomerRepository {

  private final MemoryDatabase database;

  public CustomerMemoryRepositoryImpl(MemoryDatabase database) {
    this.database = database;
  }

  @Override
  public void save(Customer customer) throws RepositoryException {
    customer.setId(database.nextId());
    database.saveOrUpdate(customer);
  }

  @Override
  public List<Customer> listAll() throws RepositoryException {
    return database.listAll(Customer.class);
  }

  @Override
  public Customer findById(Long id) throws RepositoryException {
    Customer found = database.find(
            Customer.class,
            it -> Objects.equals(id, it.getId())
    ).stream().findFirst().orElse(null);
    return found;
  }

  @Override
  public void update(Customer customer) throws RepositoryException {
    Customer inserted = findById(customer.getId());
    inserted.setName(customer.getName());
    inserted.setDocument(customer.getDocument());
    inserted.setEmail(customer.getEmail());
    inserted.setTelephone(customer.getTelephone());
    inserted.setDocument(customer.getDocument());
    database.saveOrUpdate(inserted);
  }

  @Override
  public Customer delete(Customer customer) throws RepositoryException {
    return database.delete(customer);
  }

  @Override
  public Customer findByDocument(String document) {
    Customer found = database.find(
            Customer.class,
            it -> Objects.equals(document, it.getDocument())
    ).stream().findFirst().orElse(null);
    return found;
  }

  @Override
  public List<Customer> findByName(String name) {
    return database.find(
            Customer.class,
            it -> Objects.equals(name, it.getName())
    );
  }
}
