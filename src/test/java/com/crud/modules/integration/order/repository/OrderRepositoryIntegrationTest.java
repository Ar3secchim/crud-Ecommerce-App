package com.crud.modules.integration.order.repository;

import com.crud.modules.customers.entity.Customer;
import com.crud.modules.customers.repository.CustomerRepository;
import com.crud.modules.order.entity.Order;
import com.crud.modules.order.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class OrderRepositoryIntegrationTest {
  @Autowired
  private TestEntityManager testEntityManager;

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private CustomerRepository customerRepository;

  @Test
  public void ShouldFindOrderById(){
    Customer customer = new Customer();
    customer.setIdTransaction(UUID.randomUUID().toString());
    customer.setEmail("test@example.com");
    customer.setName("Test Customer");
    customer.setPassword("testPassword");
    customer.setAddress("test. 000");

    customerRepository.save(customer);

    Order order = new Order();
    order.setIdTransaction(UUID.randomUUID().toString());
    order.setCustomer(customer);
    order.setOrderItens(new ArrayList<>());
    order.setStatus(Order.OrderStatus.OPEN);
    order.setTotal(BigDecimal.ZERO);
    order.setUpdatedAt(LocalDateTime.now());
    order.setCreatedAt(LocalDateTime.now());

    testEntityManager.persist(order);

    Order savedOrder = orderRepository.findOrderById(order.getIdTransaction());

    assertEquals(order.getIdTransaction(), savedOrder.getIdTransaction());
    assertEquals(order.getStatus(), savedOrder.getStatus());
    assertEquals(order.getOrderItens(), savedOrder.getOrderItens());
  }
}
