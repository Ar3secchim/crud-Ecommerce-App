package com.crud.modules.order.usecase;

import com.crud.modules.order.entity.Order;
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
class DeleteOrderUnitTest {
  @Mock
  private OrderRepository repository;

  @InjectMocks
  private DeleteOrder deleteOrder;

  @Test
  @DisplayName("Should exception a delete order not exist")
  public void deleteOrderInvalid(){
    when(repository.findOrderById("uni-test")).thenReturn(new Order());

    Exception exception = assertThrows(
            Exception.class, () -> deleteOrder.execute("unit-test"));

    assertEquals("Order not found", exception.getMessage());
  }

  @Test
  @DisplayName("Should exception a delete order success")
  public void deleteOrderSuccess() throws Exception {
    Order order = new Order();
    order.setSku("unit-test");

    when(repository.findOrderById("uni-test")).thenReturn(order);

    deleteOrder.execute("uni-test");

    verify(repository, times(1)).delete(any());
  }
}
