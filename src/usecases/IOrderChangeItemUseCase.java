package usecases;

import model.Order;
import model.OrderItem;
import model.Product;

public interface IOrderChangeItemUseCase {
  /*
   * 1 - Pedido precisa estar com status == OrderStatus.OPEN
   * 2 - Trocar a quantidade que foi vendida desse produto
   * 3 - Lembrar de atualizar o banco através do repository
   */
  OrderItem changeAmount(Order order, Product product, Integer amount);
}
