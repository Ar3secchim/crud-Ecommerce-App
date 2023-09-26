package com.crud.controller.dto.order;

import com.crud.model.Customer;
import com.crud.model.OrderItem;
import com.crud.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter @Getter
public class OrderResponse {
  private Integer id;
  private Customer customer;
  private List<OrderItem> items;
  private OrderStatus status;
}
