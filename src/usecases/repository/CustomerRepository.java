package usecases.repository;

import model.Customer;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
  Customer findByDocument(String document);

  List<Customer> findByName(String name);
}
