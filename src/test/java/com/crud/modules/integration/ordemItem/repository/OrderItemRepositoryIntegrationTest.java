package com.crud.modules.integration.ordemItem.repository;

import com.crud.modules.customers.entity.Customer;
import com.crud.modules.customers.repository.CustomerRepository;
import com.crud.modules.order.entity.Order;
import com.crud.modules.order.repository.OrderRepository;
import com.crud.modules.orderItem.entity.OrderItem;
import com.crud.modules.orderItem.repository.OrdemItemRepository;
import com.crud.modules.product.entity.Product;
import com.crud.modules.product.repository.ProductRepository;
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
public class OrderItemRepositoryIntegrationTest {
  @Autowired
  private TestEntityManager testEntityManager;

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private OrdemItemRepository orderItemRepository;

  @Test
  public void ShouldFindOrderItemById() {
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
    orderRepository.save(order);

    Product product= new Product();
    product.setSkuId(UUID.randomUUID().toString());
    product.setQuantityStock(10);
    product.setPrice(BigDecimal.valueOf(250));
    product.setDescription("uni-Test");
    product.setName("uni-test");
    productRepository.save(product);

    OrderItem orderItem = new OrderItem();
    orderItem.setIdTransaction(UUID.randomUUID().toString());
    orderItem.setOrder(order);
    orderItem.setProduct(product);
    orderItem.setAmount(2);
    orderItem.setPrice(BigDecimal.valueOf(250));
    orderItem.setTotal(BigDecimal.valueOf(500));

    testEntityManager.persist(orderItem);

    OrderItem orderItemFound =
            orderItemRepository.findOrderItemById(orderItem.getIdTransaction());


    assertEquals(orderItem.getIdTransaction(), orderItemFound.getIdTransaction());
    assertEquals(orderItem.getAmount(), orderItemFound.getAmount());
    assertEquals(orderItem.getTotal(), orderItemFound.getTotal());
    assertEquals(orderItem.getPrice(), orderItemFound.getPrice());
    assertEquals(orderItem.getProduct(), orderItemFound.getProduct());
  }
}
