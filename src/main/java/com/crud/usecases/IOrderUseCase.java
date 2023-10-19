package com.crud.usecases;

import com.crud.controller.dto.OrderItem.OrderItemRequest;
import com.crud.controller.dto.OrderItem.OrderItemResponse;
import com.crud.controller.dto.order.OrderRequest;
import com.crud.controller.dto.order.OrderResponse;
import com.crud.model.Customer;
import com.crud.model.Order;

import java.util.List;

public interface IOrderUseCase {
  OrderResponse create(OrderRequest OrderRequest);

  OrderItemResponse addItem(Integer orderId, OrderItemRequest orderItemRequest);

  OrderItemResponse changeAmountItem(Integer orderItemID, OrderItemRequest orderItemRequest);

  List<OrderResponse> findAll();

  Order findByCustomer(Customer customer);

  OrderResponse update(Integer orderId, Order OrderRequest);

  void removeItem(Integer ordem);
}
