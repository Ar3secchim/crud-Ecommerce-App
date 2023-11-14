package com.crud.modules.order.utils;

import com.crud.modules.order.entity.Order;
import com.crud.modules.orderItem.entity.OrderItem;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class CalculateTotal {

  public Double calculateNewTotal(Order order) {
    List<OrderItem> orderItems = order.getOrderItens();
    double newTotal = 0.0;

    for (OrderItem item : orderItems) {
      Double itemTotal = item.getTotal();
      newTotal = newTotal + itemTotal;
    }
    return newTotal;
  }
}
