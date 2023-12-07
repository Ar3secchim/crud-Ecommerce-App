package com.crud.modules.usecase.orderItem;

import com.crud.infra.queue.ReservationItemStockProducer;
import com.crud.modules.order.entity.Order;
import com.crud.modules.order.repository.OrderRepository;
import com.crud.modules.order.usecase.UpdateOrder;
import com.crud.modules.orderItem.DTO.OrderItemRequest;
import com.crud.modules.orderItem.DTO.OrderItemResponse;
import com.crud.modules.orderItem.repository.OrdemItemRepository;
import com.crud.modules.orderItem.usecase.AddItemOrderItem;
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
class AddItemOrderItemUnitTest {
  @Mock
  OrderRepository orderRepository;
  @Mock
  ProductRepository productRepository;
  @Mock
  OrdemItemRepository ordemItemRepository;
  @Mock
  UpdateOrder updateOrder;
  @Mock
  ReservationItemStockProducer reservationItemStock;

  @InjectMocks
  AddItemOrderItem addItemOrderItem;


  @BeforeEach
  public void setup() throws Exception {
    Order order = new Order();
    order.setIdTransaction("unit-test-order");
    order.setOrderItens(new ArrayList<>());

    Product product = new Product();
    product.setSkuId("unit-test-product");
    product.setPrice(BigDecimal.valueOf(250));

    when(productRepository.findProductById("unit-test-product")).thenReturn(product);
    when(orderRepository.findOrderById("unit-test-order")).thenReturn(order);
    when(updateOrder.execute("unit-test-order",order)).thenReturn(any());
  }


  @Test
  @DisplayName("should add item a order with success")
  public void AddItemOrderSuccess() throws Exception {
    OrderItemRequest orderItemUpdate = new OrderItemRequest();
    orderItemUpdate.setProductId("unit-test-product");
    orderItemUpdate.setAmount(1);

    OrderItemResponse orderItem = addItemOrderItem.execute("unit-test-order", orderItemUpdate);

    verify(ordemItemRepository, times(1)).save(any());
    verify(orderRepository, times(1)).findOrderById(any());

    assertEquals(BigDecimal.valueOf(250),orderItem.getTotal());
    assertEquals(1,orderItem.getAmount());
  }

  @Test
  @DisplayName("should return error trying to add item to order due to invalid")
  public void AddItemOrderInvalid() {
    when(orderRepository.findOrderById("unit-test-order")).thenReturn(null);

    OrderItemRequest orderItemUpdate = new OrderItemRequest();
    orderItemUpdate.setProductId("unit-test-product");
    orderItemUpdate.setAmount(1);

    Exception exception = assertThrows(Exception.class,
            () ->  addItemOrderItem.execute("unit-test-order", orderItemUpdate));
    assertEquals("Order not found",exception.getMessage());
  }

//  @Test
//  @DisplayName("should add item a order with product invalid")
//  public void AddItemOrderWithProductInvalid() {}
//
//  @Test
//  @DisplayName("should add item a order with send msg for stock invalid")
//  public void AddItemOrderSendMsgError() {}
//
//  @Test
//  @DisplayName("should return error when adding item to an order with product out of stock")
//  public void AddItemOrderSendMsgProductOutOfStock() {}
}
