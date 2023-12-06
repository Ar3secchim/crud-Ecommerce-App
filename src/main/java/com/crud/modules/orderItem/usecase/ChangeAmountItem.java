package com.crud.modules.orderItem.usecase;

import com.crud.infra.exception.BadRequestClient;
import com.crud.modules.order.entity.Order;
import com.crud.modules.order.repository.OrderRepository;
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

import java.math.BigDecimal;

@Service
public class ChangeAmountItem{
  @Autowired
  OrdemItemRepository ordemItemRepository;
  @Autowired
  ProductRepository productRepository;
  @Autowired
  OrderRepository orderRepository;
  @Autowired
  DeleteItemOrder deleteItemOrder;

  public OrderItemResponse execute(String orderItemID, OrderItemRequest orderItemRequest) throws Exception {
    OrderItem orderItem = validateOrderItem(orderItemID);

    if (!deleteOrderAmountEqualsZero(orderItemRequest)) {
      deleteOrderItem(orderItem);
    } 
    
    Product product = validateProduct(orderItemRequest.getProductSku());

    updateOrderItem(orderItem, orderItemRequest, product);
    updateOrderTotal(orderItem.getOrder());

    return OrdemItemConvert.toResponseOrderItem(orderItem);
  }

  private OrderItem validateOrderItem(String orderItemID) throws BadRequestClient {
    OrderItem orderItem = ordemItemRepository.findOrderItemById(orderItemID);
    if (orderItem == null) {
      throw new BadRequestClient("Order not found");
    }
    return orderItem;
  }

  private boolean deleteOrderAmountEqualsZero(OrderItemRequest orderItemRequest){
    return orderItemRequest.getAmount() != 0;
  }

  private void deleteOrderItem(OrderItem orderItem) {
    ordemItemRepository.delete(orderItem);
  }

  private Product validateProduct(String productSku) throws Exception {
    Product product = productRepository.findProductById(productSku);
    if (product == null) {
      throw new Exception("Product not found");
    }
    return product;
  }

  private void updateOrderItem(OrderItem orderItem, OrderItemRequest orderItemRequest, Product product) {
    orderItem.setAmount(orderItemRequest.getAmount());
    orderItem.setTotal(product.getPrice().multiply(BigDecimal.valueOf(orderItemRequest.getAmount())));
    ordemItemRepository.save(orderItem);
  }

  private void updateOrderTotal(Order order) {
    order.setTotal(CalculateTotal.execute(order));
    orderRepository.save(order);
  }
}
