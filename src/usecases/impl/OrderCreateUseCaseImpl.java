package usecases.impl;

import model.Customer;
import model.Order;
import model.OrderStatus;
import usecases.ICreateOrderUseCase;
import usecases.repository.CustomerRepository;
import usecases.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderCreateUseCaseImpl implements ICreateOrderUseCase {

  private OrderRepository orderRepository;
  private CustomerRepository customerRepository;

  public OrderCreateUseCaseImpl(OrderRepository repository, CustomerRepository customerRepository) {
    this.orderRepository = repository;
    this.customerRepository = customerRepository;
  }

  private void validCustomer(Customer customer) {
    Customer found = customerRepository.findByDocument(customer.getDocument());
    if (found == null) {
      throw new IllegalStateException("Cliente não encontrado");
    }
  }

  private Order createNewOrder(Customer customer) {
    Order order = new Order();
    order.setCustomer(customer);
    order.setItems(new ArrayList<>());
    order.setStatus(OrderStatus.OPEN);
    order.setShippingAddress("Minha casa sempre");
    order.setOrderedAt(LocalDateTime.now());

    return order;
  }

  @Override
  public Order create(Customer customer) {
    validCustomer(customer);

    Order newOrder = createNewOrder(customer);

    orderRepository.save(newOrder);
    return newOrder;
  }
}
