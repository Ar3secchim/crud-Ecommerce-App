package com.crud.utils;

import com.crud.controller.dto.OrderItem.OrderItemRequest;
import com.crud.controller.dto.OrderItem.OrderItemResponse;
import com.crud.controller.dto.order.OrderResponse;
import com.crud.model.*;

import java.util.ArrayList;
import java.util.List;

public class OrdemItemConvert {
    public static OrderItem toEntity(OrderItemRequest orderRequest, Order order, Product product){
      OrderItem orderItem = new OrderItem();

      orderItem.setOrder(order);
      orderItem.setProduct(product);
      orderItem.setAmount(orderRequest.getAmount());
      orderItem.setPrice(product.getPrice());
      orderItem.setTotal((orderRequest.getAmount() * product.getPrice()));
      return orderItem;
    }

    public static OrderItemResponse toResponseOrderItem(OrderItem orderItem){
      OrderItemResponse orderItemResponse = new OrderItemResponse();

      orderItemResponse.setOrderId(orderItem.getOrder().getId());
      orderItemResponse.setProduct(ProductConvert.toResponse(orderItem.getProduct()));
      orderItemResponse.setAmount(orderItem.getAmount());
      orderItemResponse.setTotal(orderItem.getTotal());

      return orderItemResponse;
    }

    public static List<OrderItemResponse> toResponseList(List<OrderItem> orders){
      List<OrderItemResponse> ordersResponse = new ArrayList<>();
      for(OrderItem order: orders){
        ordersResponse.add(toResponseOrderItem(order));
      }
      return ordersResponse;
    }
}
