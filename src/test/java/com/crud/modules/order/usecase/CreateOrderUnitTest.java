package com.crud.modules.order.usecase;

import com.crud.modules.customers.entity.Customer;
import com.crud.modules.customers.repository.CustomerRepository;
import com.crud.modules.order.DTO.OrderRequest;
import com.crud.modules.order.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class CreateOrderUnitTest {
  @Mock
  private CustomerRepository repository;
  @Mock
  private OrderRepository orderRepository;
  @InjectMocks
  private  CreateOrder createOrder;

  @Test
  @DisplayName("should register order when everything success")
  public void createOrderSuccess() throws Exception {
    Customer customer = new Customer();
    customer.setSku("uni-test");

    when(repository.findBySku(customer.getSku())).thenReturn(customer);

    OrderRequest order = new OrderRequest();
    order.setCustomerSku(customer.getSku());

    createOrder.execute(order);
    verify(orderRepository, times(1)).save(any());
    assertEquals("uni-test", order.getCustomerSku());
  }

  @Test
  @DisplayName("should register order when customer invalid")
  public void createOrderWithCustomerInvalid(){
    Exception exeption = assertThrows(
            Exception.class, () -> createOrder.execute(new OrderRequest()));

    assertEquals("Customer not found", exeption.getMessage());
  }
}
