package usecases.impl;

import model.Customer;
import usecases.ICustomerUseCase;
import usecases.INotifierUserCase;
import usecases.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

public class CustomerUseCaseImpl implements ICustomerUseCase {

  private CustomerRepository repository;
  private INotifierUserCase<Customer> notifier;

  public CustomerUseCaseImpl(CustomerRepository repository, INotifierUserCase<Customer> notifier) {
    this.repository = repository;
    this.notifier = notifier;
  }

  @Override
  public void create(Customer customer) {
    repository.save(customer);
    notifier.registered(customer);
  }

  @Override
  public List<Customer> list() {
    return repository.listAll();
  }

  @Override
  public Customer findByDocument(String document) {
    Customer found = null;
    if (document != null) {
      found = repository.findByDocument(document);
    }
    return found;
  }

  @Override
  public List<Customer> findByName(String name) {
    List<Customer> found = new ArrayList<>();
    if (name != null) {
      found = repository.findByName(name);
    }
    return found;
  }

  @Override
  public void update(Customer customer) {
    repository.update(customer);
    notifier.updated(customer);
  }

  @Override
  public Customer delete(Long id) {
    Customer customer = repository.findById(id);
    if (customer != null) {
      repository.delete(customer);
      notifier.deleted(customer);
    }
    return customer;
  }

}
