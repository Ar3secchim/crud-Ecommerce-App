package com.crud.modules.order.DTO;


import com.crud.modules.order.entity.Order;
import com.crud.modules.orderItem.DTO.OrderItemResponse;
import lombok.Data;

import java.util.List;

@Data
public class OrderResponse {
  private String sku;
  private String customer;
  private List<OrderItemResponse> items;
  private Order.OrderStatus status;
  private Double total;
}
