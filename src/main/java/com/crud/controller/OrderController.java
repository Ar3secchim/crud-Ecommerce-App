package com.crud.controller;

import com.crud.controller.dto.order.OrderRequest;
import com.crud.controller.dto.order.OrderResponse;
import com.crud.model.OrderItem;
import com.crud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class OrderController {

  @Autowired
  OrderService orderService;

  @PostMapping("/order")
  public OrderResponse createOrder(@RequestBody OrderRequest orderRequest){
    return orderService.create(orderRequest);
  }

  @GetMapping("/order")
  //TODO error  Index 48 out of bounds for length 5
  public List<OrderResponse> getOrder(){
    return orderService.listAll();
  }

  @PostMapping("/order/{orderId}")
  public OrderItem addItem(@PathVariable Integer orderId, @RequestBody OrderItem orderItem){
    return orderService.addItem(orderId, orderItem);
  }

}
