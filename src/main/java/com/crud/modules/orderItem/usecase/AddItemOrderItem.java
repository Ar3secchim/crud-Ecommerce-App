package com.crud.modules.orderItem.usecase;

import com.crud.infra.queue.DTO.StockReservationRequest;
import com.crud.infra.queue.ReservationItemStockProducer;
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
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class AddItemOrderItem {
  @Autowired
  OrderRepository orderRepository;
  @Autowired
  ProductRepository productRepository;
  @Autowired
  OrdemItemRepository ordemItemRepository;
  @Autowired
  UpdateOrder updateOrder;
  @Autowired
  ReservationItemStockProducer ReservationItemStock;

  public OrderItemResponse execute(String orderId, OrderItemRequest orderItemRequest) throws Exception {
    Order order = orderRepository.findOrderById(orderId);
    Product product = productRepository.findProductById(orderItemRequest.getProductSku());

    OrderItem newItem = OrdemItemConvert.toEntity(orderItemRequest, order, product);
    ordemItemRepository.save(newItem);
    order.getOrderItens().add(newItem);

    reservarItems(orderId, product);
    order.setTotal(CalculateTotal.execute(order));

    updateOrder.execute(orderId, order);

    return OrdemItemConvert.toResponseOrderItem(newItem);
  }

  private void reservarItems(String skuId, Product item) {
    StockReservationRequest reservarEstoqueRequest = new StockReservationRequest();
    reservarEstoqueRequest.setSkuId(skuId);
    reservarEstoqueRequest.setItem(item);

    try {
      ReservationItemStock.enviar(reservarEstoqueRequest);
    } catch (JsonProcessingException e) {
      log.error("Não foi possível enviar a mensagem ao destinatário", e);
      throw new RuntimeException(e);
    }
  }
}
