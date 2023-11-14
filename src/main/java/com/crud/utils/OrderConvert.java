package com.crud.utils;

import com.crud.modules.orderItem.DTO.OrderItemResponse;
import com.crud.modules.order.DTO.OrderResponse;
import com.crud.modules.customers.entity.Customer;
import com.crud.modules.order.entity.Order;


import java.time.LocalDateTime;
import java.util.*;

public class OrderConvert {
  public static Order toEntity(Customer customer){
    Order order = new Order();

    order.setSku(UUID.randomUUID().toString());
    order.setCustomer(customer);
    order.setStatus(Order.OrderStatus.OPEN);
    order.setOrderItens(new ArrayList<>());
    order.setCreatedAt(LocalDateTime.now());
    order.setUpdatedAt(LocalDateTime.now());
    order.setTotal(0.0);
    return order;
  }

  public static OrderResponse toResponseOrder(Order order){
    OrderResponse orderResponse = new OrderResponse();
    List<OrderItemResponse> orderItemsResponse = OrdemItemConvert.toResponseList(order.getOrderItens());

    orderResponse.setSku(order.getSku());
    orderResponse.setCustomer(order.getCustomer().getSku());
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
