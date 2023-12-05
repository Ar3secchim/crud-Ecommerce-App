package com.crud.utils;

import com.crud.modules.orderItem.DTO.OrderItemRequest;
import com.crud.modules.orderItem.DTO.OrderItemResponse;
import com.crud.modules.order.entity.Order;
import com.crud.modules.orderItem.entity.OrderItem;
import com.crud.modules.product.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrdemItemConvert {
    public static OrderItem toEntity(OrderItemRequest orderItemRequest, Order order, Product product){
      OrderItem orderItem = new OrderItem();

      orderItem.setSku(UUID.randomUUID().toString());
      orderItem.setOrder(order);
      orderItem.setProduct(product);
      orderItem.setAmount(orderItemRequest.getAmount());
      orderItem.setPrice(product.getPrice());
      orderItem.setTotal((orderItemRequest.getAmount() * product.getPrice()));
      return orderItem;
    }

    public static OrderItemResponse toResponseOrderItem(OrderItem orderItem){
      OrderItemResponse orderItemResponse = new OrderItemResponse();

      orderItemResponse.setOrderSku(orderItem.getSku());
      orderItemResponse.setProduct(ProductConvert.toResponse(orderItem.getProduct()));
      orderItemResponse.setAmount(orderItem.getAmount());
      orderItemResponse.setTotal(orderItem.getTotal());

      return orderItemResponse;
    }

    public static List<OrderItemResponse> toResponseList(List<OrderItem> orders){
      List<OrderItemResponse> ordersResponse = new ArrayList<>();

      if(orders == null) return new ArrayList<>();

      for(OrderItem order: orders){
        ordersResponse.add(toResponseOrderItem(order));
      }
      return ordersResponse;
    }
}
