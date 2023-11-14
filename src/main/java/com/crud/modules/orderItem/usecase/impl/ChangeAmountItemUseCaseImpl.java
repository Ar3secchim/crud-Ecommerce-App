package com.crud.modules.orderItem.usecase.impl;

import com.crud.modules.order.entity.Order;
import com.crud.modules.order.repository.OrderRepository;
import com.crud.modules.order.utils.CalculateTotal;
import com.crud.modules.orderItem.DTO.OrderItemRequest;
import com.crud.modules.orderItem.DTO.OrderItemResponse;
import com.crud.modules.orderItem.entity.OrderItem;
import com.crud.modules.orderItem.repository.OrdemItemRepository;
import com.crud.modules.orderItem.usecase.IChangeAmountItemUseCase;
import com.crud.modules.product.entity.Product;
import com.crud.modules.product.repository.ProductRepository;
import com.crud.utils.OrdemItemConvert;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChangeAmountItemUseCaseImpl implements IChangeAmountItemUseCase {
  @Autowired
  OrdemItemRepository ordemItemRepository;
  @Autowired
  ProductRepository productRepository;
  @Autowired
  OrderRepository orderRepository;

  CalculateTotal calculateTotal;
  @Override
  public OrderItemResponse changeAmountItem(String orderItemID, OrderItemRequest orderItemRequest) {
    Optional<OrderItem> optionalOrderItem = ordemItemRepository.findById(orderItemID);

    if (optionalOrderItem.isPresent()) {
      OrderItem orderItem = optionalOrderItem.get();
      Product product = productRepository.findById(orderItemRequest.getProductSku()).get();

      orderItem.setAmount(orderItemRequest.getAmount());
      orderItem.setTotal(orderItemRequest.getAmount() * product.getPrice());

      ordemItemRepository.save(orderItem);

      Order order = orderItem.getOrder();
      order.setTotal(calculateTotal.calculateNewTotal(order));

      orderRepository.save(order);

      return OrdemItemConvert.toResponseOrderItem(orderItem);
    } else {
      throw new EntityNotFoundException("Item de pedido não encontrado com ID: " + orderItemID);
    }
  }
}
