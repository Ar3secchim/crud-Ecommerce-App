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

  public OrderItemResponse changeAmountItem(String orderItemID, OrderItemRequest orderItemRequest) throws Exception {

    OrderItem orderItem = ordemItemRepository.findOrderItemById(orderItemID);

    if(orderItem == null){
      throw new BadRequestClient("Order não encontrada");
    }

    if(!deleteOrderAmountEqualsZero(orderItemRequest))
      ordemItemRepository.delete(orderItem);

    Product product = productRepository.findProductById(orderItem.getProduct().getSku());

    if(product == null) throw new Exception("Product not found");

    orderItem.setAmount(orderItemRequest.getAmount());
    orderItem.setTotal(product.getPrice().multiply(BigDecimal.valueOf(orderItemRequest.getAmount())));

    Order order = orderItem.getOrder();
    order.setTotal(CalculateTotal.execute(order));

    ordemItemRepository.save(orderItem);
    orderRepository.save(order);

    return OrdemItemConvert.toResponseOrderItem(orderItem);
  }

  private boolean deleteOrderAmountEqualsZero(OrderItemRequest orderItemRequest){
    return orderItemRequest.getAmount() != 0;
  }
}
