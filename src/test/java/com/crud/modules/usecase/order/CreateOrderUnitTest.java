package com.crud.modules.usecase.order;

import com.crud.modules.customers.entity.Customer;
import com.crud.modules.customers.repository.CustomerRepository;
import com.crud.modules.order.DTO.OrderRequest;
import com.crud.modules.order.repository.OrderRepository;
import com.crud.modules.order.usecase.CreateOrder;
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
  private CreateOrder createOrder;

  @Test
  @DisplayName("should register order when everything success")
  public void createOrderSuccess() throws Exception {
    Customer customer = new Customer();
    customer.setIdTransaction("uni-test");

    when(repository.findByIdTransaction(customer.getIdTransaction())).thenReturn(customer);

    OrderRequest order = new OrderRequest();
    order.setCustomerId(customer.getIdTransaction());

    createOrder.execute(order);
    verify(orderRepository, times(1)).save(any());
    assertEquals("uni-test", order.getCustomerId());
  }

  @Test
  @DisplayName("should register order when customer invalid")
  public void createOrderWithCustomerInvalid(){
    Exception exeption = assertThrows(
            Exception.class, () -> createOrder.execute(new OrderRequest()));

    assertEquals("Customer not found", exeption.getMessage());
  }
}
