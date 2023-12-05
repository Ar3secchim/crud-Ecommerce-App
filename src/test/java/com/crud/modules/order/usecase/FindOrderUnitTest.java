package com.crud.modules.order.usecase;

import com.crud.modules.customers.entity.Customer;
import com.crud.modules.order.entity.Order;
import com.crud.modules.order.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class FindOrderUnitTest {
  @Mock
  private OrderRepository repository;
  @InjectMocks
  private FindOrder findOrder;

  @Test
  @DisplayName("should all order")
  public void findAllOrder(){
   Order order = new Order();
   order.setSku("uni-test");
   order.setCustomer(new Customer());

    when(repository.findAll()).thenReturn(List.of(order));
    findOrder.findAll();

    verify(repository, times(1)).findAll();
  }

  @Test
  @DisplayName("should order find by id success")
  public void findOrderByIdSuccess() throws Exception {
    Order order = new Order();
    order.setSku("uni-test");
    order.setCustomer(new Customer());

    when(repository.findOrderById(order.getSku())).thenReturn(order);
    findOrder.findById(order.getSku());

    verify(repository, times(1)).findOrderById(any());
  }

  @Test
  @DisplayName("should order find by id invalid")
  public void findOrderByIdInvalid() {
    when(repository.findOrderById("uni-test")).thenReturn(any());

    Exception exception = assertThrows(Exception.class,
            () -> findOrder.findById("uni-test"));

    assertEquals("Order not found", exception.getMessage());
  }
}
