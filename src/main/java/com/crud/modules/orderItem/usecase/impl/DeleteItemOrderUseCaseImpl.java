package com.crud.modules.orderItem.usecase.impl;

import com.crud.modules.order.entity.Order;
import com.crud.modules.order.repository.OrderRepository;
import com.crud.modules.order.usecase.impl.UpdateOrderUseCaseImpl;
import com.crud.modules.orderItem.entity.OrderItem;
import com.crud.modules.orderItem.repository.OrdemItemRepository;
import com.crud.modules.orderItem.usecase.IDeleteItemOrderUseCase;
import com.crud.modules.product.entity.Product;
import com.crud.modules.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeleteItemOrderUseCaseImpl implements IDeleteItemOrderUseCase {
  @Autowired
  ProductRepository productRepository;
  @Autowired
  OrdemItemRepository ordemItemRepository;
  @Autowired
  UpdateOrderUseCaseImpl updateOrderService;

  @Override
  public void deleteItem(String ordem) {
    OrderItem inserted = ordemItemRepository.findOrderItemById(ordem);
    Order order = inserted.getOrder();

    Product items = inserted.getProduct();
    Product product = productRepository.findProductById(items.getSku());

    List<OrderItem> listItems = order.getOrderItens();

    if (product == null) {
      throw new IllegalStateException("Produto não encontrado");
    }

    for (OrderItem item : listItems) {
      if (item.getProduct().equals(items)) {
        listItems.remove(0);
        break;
      }
    }

    order.setOrderItens(listItems);
    updateOrderService.update(order.getSku(), order);
  }
}
