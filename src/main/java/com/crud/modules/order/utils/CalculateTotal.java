package com.crud.modules.order.utils;

import com.crud.modules.order.entity.Order;
import com.crud.modules.orderItem.entity.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public class CalculateTotal {
  public static BigDecimal execute(Order order) {
    List<OrderItem> orderItems = order.getOrderItens();
    BigDecimal newTotal = BigDecimal.ZERO;

    for (OrderItem item : orderItems) {
      BigDecimal itemTotal = item.getTotal();
      newTotal = newTotal.add(itemTotal);
    }

    return newTotal;
  }
}
