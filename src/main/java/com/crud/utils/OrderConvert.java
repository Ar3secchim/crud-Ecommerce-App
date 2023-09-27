package com.crud.utils;

import com.crud.controller.dto.order.OrderRequest;
import com.crud.controller.dto.order.OrderResponse;
import com.crud.model.Customer;
import com.crud.model.Order;
import com.crud.model.OrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderConvert {
  public static Order toEntity(OrderRequest orderRequest, Customer customer){
    Order order = new Order();

    order.setCustomer(customer);
    order.setStatus(OrderStatus.OPEN);
    order.setItems(new ArrayList<>());
    order.setOrderedAt(LocalDateTime.now());
    order.setShippingAddress(orderRequest.getShippingAddress());
    return order;
  }

  public static OrderResponse toResponseOrder(Order order){
    OrderResponse orderResponse = new OrderResponse();

    orderResponse.setId(order.getId());
    orderResponse.setCustomer(order.getCustomer());
    orderResponse.setItems(order.getItems());
    orderResponse.setStatus(order.getStatus());

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
