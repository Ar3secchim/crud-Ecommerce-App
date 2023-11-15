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

  CalculateTotal calculateTotal = new CalculateTotal();

  public OrderItemResponse changeAmountItem(String orderItemID, OrderItemRequest orderItemRequest) throws BadRequestClient {

    OrderItem orderItem = ordemItemRepository.findOrderItemById(orderItemID);

    if(orderItem == null){
      throw new BadRequestClient("Order não encontrada");
    }

    if(!deleteOrderAmountEqualsZero(orderItemRequest)){
      throw new BadRequestClient("Item não pode ser menor que zero caso seja isso por favor exclua o item do pedido");
    }else{
      Product product = productRepository.findProductById(orderItem.getProduct().getSku());

      orderItem.setAmount(orderItemRequest.getAmount());
      orderItem.setTotal(orderItemRequest.getAmount() * product.getPrice());

      Order order = orderItem.getOrder();
      order.setTotal(calculateTotal.calculateNewTotal(order));

      ordemItemRepository.save(orderItem);
      orderRepository.save(order);

      return OrdemItemConvert.toResponseOrderItem(orderItem);
    }
  }

  private boolean deleteOrderAmountEqualsZero(OrderItemRequest orderItemRequest){
    return orderItemRequest.getAmount() != 0;
  }
}
