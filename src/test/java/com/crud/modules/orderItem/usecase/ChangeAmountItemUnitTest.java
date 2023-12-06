package com.crud.modules.orderItem.usecase;

import com.crud.modules.order.entity.Order;
import com.crud.modules.order.repository.OrderRepository;
import com.crud.modules.orderItem.DTO.OrderItemRequest;
import com.crud.modules.orderItem.DTO.OrderItemResponse;
import com.crud.modules.orderItem.entity.OrderItem;
import com.crud.modules.orderItem.repository.OrdemItemRepository;
import com.crud.modules.product.entity.Product;
import com.crud.modules.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
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
class ChangeAmountItemUnitTest {
  @Mock
  OrdemItemRepository ordemItemRepository;
  @Mock
  ProductRepository productRepository;
  @Mock
  OrderRepository orderRepository;

  @InjectMocks
  private ChangeAmountItem changeAmountItem;

  @BeforeEach
  public void setup(){
    Order order = new Order();
    order.setSku("unit-test-order");
    order.setOrderItens(new ArrayList<>());

    OrderItem orderItemExist = new OrderItem();
    orderItemExist.setSku("unit-test");
    orderItemExist.setOrder(order);

    Product product = new Product();
    product.setSku("unit-test-product");
    product.setPrice(BigDecimal.valueOf(250));

    when(ordemItemRepository.findOrderItemById("unit-test")).thenReturn(orderItemExist);
    when(productRepository.findProductById("unit-test-product")).thenReturn(product);
    when(orderRepository.findOrderById("unit-test-order")).thenReturn(order);
  }

  @Test
  @DisplayName("should change amount item a orderItem for success")
  public void changeAmountItemSuccess() throws Exception {
    OrderItemRequest orderItemUpdate = new OrderItemRequest();
    orderItemUpdate.setProductSku("unit-test-product");
    orderItemUpdate.setAmount(1);

    OrderItemResponse orderItem = changeAmountItem.changeAmountItem("unit-test", orderItemUpdate);

    verify(ordemItemRepository, times(1)).save(any());
    verify(orderRepository, times(1)).save(any());

    assertEquals(BigDecimal.valueOf(250),orderItem.getTotal());
    assertEquals(1,orderItem.getAmount());
    assertEquals("unit-test-order",orderItem.getOrderSku());
  }

  @Test
  @DisplayName("should change amount item a orderItem invalid")
  public void changeAmountItemInvalid() throws Exception {
    when(ordemItemRepository.findOrderItemById("unit-test")).thenReturn(null);

    OrderItemRequest orderItemUpdate = new OrderItemRequest();
    orderItemUpdate.setProductSku("unit-test-product");
    orderItemUpdate.setAmount(1);

    Exception exception = assertThrows(Exception.class,
            () ->  changeAmountItem.changeAmountItem("unit-test", orderItemUpdate));
    assertEquals("Order not found",exception.getMessage());
  }

  @Test
  @DisplayName("should change amount item a orderItem for product invalid")
  public void changeAmountItemProductInvalid() {
    when(productRepository.findProductById("unit-test-product")).thenReturn(null);

    OrderItemRequest orderItemUpdate = new OrderItemRequest();
    orderItemUpdate.setProductSku("unit-test-product");
    orderItemUpdate.setAmount(1);

    Exception exception = assertThrows(Exception.class,
            () ->  changeAmountItem.changeAmountItem("unit-test", orderItemUpdate));
    assertEquals("Product not found",exception.getMessage());
  }

  @Test
  @DisplayName("should change amount item a orderItem for product equals zero")
  public void changeAmountItemProductEqualsZero() throws Exception {
    OrderItemRequest orderItemUpdate = new OrderItemRequest();
    orderItemUpdate.setProductSku("unit-test-product");
    orderItemUpdate.setAmount(0);

    changeAmountItem.changeAmountItem("unit-test", orderItemUpdate);

    verify(ordemItemRepository, times(1)).save(any());
    verify(orderRepository, times(1)).save(any());
    verify(ordemItemRepository, times(1)).delete(any());
  }
}
