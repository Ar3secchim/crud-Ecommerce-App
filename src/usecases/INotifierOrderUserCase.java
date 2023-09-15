package usecases;

import model.Order;

public interface INotifierOrderUserCase {

  void shipping(Order order);

  void updatedPayOrder(Order order);

  void pendingPayment(Order order);
}
