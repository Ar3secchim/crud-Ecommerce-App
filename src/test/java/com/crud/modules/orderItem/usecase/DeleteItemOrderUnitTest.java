package com.crud.modules.orderItem.usecase;

import com.crud.modules.order.DTO.OrderRequest;
import com.crud.modules.orderItem.entity.OrderItem;
import com.crud.modules.orderItem.repository.OrdemItemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class DeleteItemOrderUnitTest {
  @Mock
  OrdemItemRepository ordemItemRepository;
  @InjectMocks
  DeleteItemOrder deleteItemOrder;

  @Test
  @DisplayName("should delete orderItem for success")
  public void deleteOrderItemSuccess() throws Exception {
    when(ordemItemRepository.findOrderItemById("unit-test")).thenReturn(new OrderItem());

    deleteItemOrder.execute("unit-test");
    verify(ordemItemRepository, times(1)).delete(any());
  }

  @Test
  @DisplayName("should delete orderItem for invalid")
  public void deleteOrderItemInvalid(){
    Exception exeption = assertThrows(
            Exception.class, () -> deleteItemOrder.execute(null));

    assertEquals("Order not found", exeption.getMessage());
  }
}
