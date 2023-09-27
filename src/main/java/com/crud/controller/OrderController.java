package com.crud.controller;

import com.crud.controller.dto.OrderItem.OrderItemRequest;
import com.crud.controller.dto.order.OrderRequest;
import com.crud.controller.dto.order.OrderResponse;
import com.crud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/order")
public class OrderController {

  @Autowired
  OrderService orderService;

  @PostMapping
  public OrderResponse createOrder(@RequestBody OrderRequest orderRequest){
    return orderService.create(orderRequest);
  }

  @GetMapping
  //TODO error  Index 48 out of bounds for length 5
  public List<OrderResponse> getOrder(){
    return orderService.findAllOrders();
  }

//  @PostMapping("/order/{orderId}")
////  public OrderResponse addItem(@PathVariable Integer orderId, @RequestBody OrderItemRequest orderItem){
////    return orderService.addItem(orderId, orderItem);
////  }

}
