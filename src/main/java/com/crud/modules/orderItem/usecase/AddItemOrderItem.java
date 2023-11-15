package com.crud.modules.orderItem.usecase;

import com.crud.modules.order.entity.Order;
import com.crud.modules.order.repository.OrderRepository;
import com.crud.modules.order.usecase.UpdateOrder;
import com.crud.modules.order.utils.CalculateTotal;
import com.crud.modules.orderItem.DTO.OrderItemRequest;
import com.crud.modules.orderItem.DTO.OrderItemResponse;
import com.crud.modules.orderItem.entity.OrderItem;
import com.crud.modules.orderItem.repository.OrdemItemRepository;
import com.crud.modules.product.entity.Product;
import com.crud.modules.product.repository.ProductRepository;
import com.crud.utils.OrdemItemConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddItemOrderItem {
  @Autowired
  OrderRepository orderRepository;
  @Autowired
  ProductRepository productRepository;
  @Autowired
  OrdemItemRepository ordemItemRepository;
  @Autowired
  UpdateOrder updateOrder;

  CalculateTotal calculateTotal= new CalculateTotal();

  public OrderItemResponse execute(String orderId, OrderItemRequest orderItemRequest) {
    Order order = orderRepository.findOrderById(orderId);
    Product product = productRepository.findProductById(orderItemRequest.getProductSku());

    OrderItem newItem = OrdemItemConvert.toEntity(orderItemRequest, order, product);
    ordemItemRepository.save(newItem);
    order.getOrderItens().add(newItem);
    order.setTotal(calculateTotal.calculateNewTotal(order));

    updateOrder.execute(orderId, order);
    return OrdemItemConvert.toResponseOrderItem(newItem);
  }

}
