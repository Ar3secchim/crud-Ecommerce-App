package com.crud.ecommerce.app.application.impl;

import com.crud.ecommerce.app.application.IOrderRemoveItemUseCase;
import com.crud.ecommerce.app.application.repository.OrderRepository;
import com.crud.ecommerce.app.domain.Order;
import com.crud.ecommerce.app.domain.OrderItem;
import com.crud.ecommerce.app.domain.OrderStatus;
import com.crud.ecommerce.app.domain.Product;


import java.util.List;

public class OrderRemoveItemUseImpl implements IOrderRemoveItemUseCase {
  private final OrderRepository orderRepository;

  public OrderRemoveItemUseImpl(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Override
  public void removeItem(Order order, Product product) {
    if (order.getStatus() != OrderStatus.OPEN) {
      throw new IllegalStateException("Não pode remover item no pedido");
    }

    List<OrderItem> listItems = order.getItems();

    Product productDelete = getProductFromOrder(product, listItems);
    if (productDelete == null) {
      throw new IllegalStateException("Produto não encontrado");
    }

    Order orderUpdate = deleteProductFromOrder(productDelete, listItems, order);
    orderRepository.update(orderUpdate);
  }

  private Product getProductFromOrder(Product product, List<OrderItem> listItems) {
    Product itemDelete = null;

    for (OrderItem item : listItems) {
      if (item.getProduct().equals(product)) {
        itemDelete = item.getProduct();
        break;
      }
    }
    return itemDelete;
  }

  private Order deleteProductFromOrder(Product productDelete, List<OrderItem> listItems, Order order) {
    for (OrderItem item : listItems) {
      if (item.getProduct().equals(productDelete)) {
        listItems.remove(item);
        break;
      }
    }
    order.setItems(listItems);
    return order;
  }
}
