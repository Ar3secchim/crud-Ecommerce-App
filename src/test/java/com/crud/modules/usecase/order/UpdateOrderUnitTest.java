package com.crud.modules.usecase.order;

import com.crud.modules.customers.entity.Customer;
import com.crud.modules.order.entity.Order;
import com.crud.modules.order.repository.OrderRepository;
import com.crud.modules.order.usecase.UpdateOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class UpdateOrderUnitTest {
  @Mock
  private OrderRepository repository;
  @InjectMocks
  private UpdateOrder updateOrder;


  @Test
  @DisplayName("should order update success")
  public void updateOrderSuccess() throws Exception {
    Order order = new Order();
    order.setIdTransaction("unit-test");
    order.setOrderItens(new ArrayList<>());
    order.setCustomer(new Customer());

    when(repository.findOrderById("unit-test")).thenReturn(order);

    Order orderInput = new Order();
    orderInput.setIdTransaction("unit-test");
    orderInput.setStatus(Order.OrderStatus.OPEN);
    orderInput.setOrderItens(new ArrayList<>());

    var orderUpdate = updateOrder.execute(order.getIdTransaction(), orderInput);

    verify(repository, times(1)).save(any());
    assertEquals( BigDecimal.ZERO, orderUpdate.getTotal());
  }

  @Test
  @DisplayName("should order update invalid")
  public void updateOrderInvalid() throws Exception {
    when(repository.findOrderById("unit-test")).thenReturn(null);

    Exception exception = assertThrows(Exception.class,
            () ->   updateOrder.execute("unit-test", new Order()));

    assertEquals("Order not found", exception.getMessage());
  }
}
