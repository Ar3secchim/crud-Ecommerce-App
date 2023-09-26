package com.crud.service;

import com.crud.controller.dto.order.OrderRequest;
import com.crud.controller.dto.order.OrderResponse;
import com.crud.model.Customer;
import com.crud.model.Order;
import com.crud.model.OrderItem;
import com.crud.model.Product;
import com.crud.repository.CustomerRepository;
import com.crud.repository.OrderRepository;
import com.crud.utils.OrderConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {
  @Autowired
  OrderRepository orderRepository;
  @Autowired
  CustomerRepository customerRepository;

  public OrderResponse create(OrderRequest orderResquest) {
    Customer customer = customerRepository.findById(orderResquest.getCustomer());
    Order order = OrderConvert.toEntity(orderResquest, customer);
    return  OrderConvert.toResponseOrder(orderRepository.save(order));
  }

  public List<OrderResponse> listAll(){
    return OrderConvert.toListResponse(orderRepository.findAll());
  }

  public OrderItem addItem(Integer orderId, OrderItem orderItem) {
    Order order = orderRepository.findById(orderId).get();

    OrderItem newItem = new OrderItem();
    newItem.setProduct(orderItem.getProduct());
    newItem.setSaleValue(orderItem.getSaleValue());
    newItem.setAmount(orderItem.getAmount());

    order.getItems().add(newItem);
    return newItem;
  }
}
