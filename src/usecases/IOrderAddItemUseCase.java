package usecases;

import model.Order;
import model.OrderItem;
import model.Product;

import java.math.BigDecimal;

public interface IOrderAddItemUseCase {
  /*
   * 1 - Pedido precisa estar com status == OrderStatus.OPEN
   * 2 - Lembrar de atualizar o banco através do repository
   */
  OrderItem addItem(Order order, Product product, BigDecimal price, Integer amount);
}
