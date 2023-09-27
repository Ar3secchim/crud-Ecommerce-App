package com.crud.service;

import com.crud.controller.dto.order.OrderRequest;
import com.crud.controller.dto.order.OrderResponse;
import com.crud.model.Customer;
import com.crud.model.Order;
import com.crud.repository.CustomerRepository;
import com.crud.repository.OrderRepository;
import com.crud.repository.ProductRepository;
import com.crud.utils.OrderConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
  @Autowired
  OrderRepository orderRepository;
  @Autowired
  CustomerRepository customerRepository;
  @Autowired
  ProductRepository productRepository;

  public OrderResponse create(OrderRequest orderRequest) {
    Customer customer = customerRepository.findById(orderRequest.getCustomer());
    Order order = OrderConvert.toEntity(orderRequest, customer);
    return  OrderConvert.toResponseOrder(orderRepository.save(order));
  }

  public List<OrderResponse> findAllOrders(){
    return OrderConvert.toResponseList(orderRepository.findAll());
  }

//  public OrderResponse addItem(Integer orderId, OrderItemRequest orderItem) {
//    Order order = orderRepository.findById(orderId).get();
//    Product product = productRepository.findAllById(orderItem.getProduct());
//
//    OrderItem newItem = new OrderItem();
//    newItem.setProduct(product);
//    newItem.setSaleValue(product.getPrice());
//    newItem.setAmount(orderItem.getAmount());
//
//    order.getItems().add(newItem);
//
//    return  OrderConvert.toResponseOrder(orderRepository.update(order));
//  }
}
