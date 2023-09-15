package usecases.repository;

import model.Customer;
import model.Order;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
  List<Order> findByCustomer(Customer customer);
}
