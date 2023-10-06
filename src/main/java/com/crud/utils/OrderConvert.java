package com.crud.utils;

import com.crud.controller.dto.OrderItem.OrderItemResponse;
import com.crud.controller.dto.order.OrderRequest;
import com.crud.controller.dto.order.OrderResponse;
import com.crud.model.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class OrderConvert {
  public static Order toEntity(Customer customer){
    Order order = new Order();

    order.setCustomer(customer);
    order.setStatus(OrderStatus.OPEN);
    order.setOrderItens(new ArrayList<>());
    order.setCreatedAt(LocalDateTime.now());
    order.setUpdatedAt(LocalDateTime.now());
    order.setTotal(0.0);
    return order;
  }

  public static OrderResponse toResponseOrder(Order order){
    OrderResponse orderResponse = new OrderResponse();
    List<OrderItemResponse> orderItemsResponse = OrdemItemConvert.toResponseList(order.getOrderItens());

    orderResponse.setId(order.getId());
    orderResponse.setCustomer(order.getCustomer().getId());
    orderResponse.setItems(orderItemsResponse);
    orderResponse.setStatus(order.getStatus());
    orderResponse.setTotal(order.getTotal());

    return orderResponse;
  }

  public static List<OrderResponse> toResponseList(List<Order> orders){
    List<OrderResponse> ordersResponse = new ArrayList<>();
    for(Order order: orders){
      ordersResponse.add(toResponseOrder(order));
    }
    return ordersResponse;
  }
}
