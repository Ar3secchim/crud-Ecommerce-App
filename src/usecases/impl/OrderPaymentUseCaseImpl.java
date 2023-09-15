package usecases.impl;

import model.Order;
import model.OrderStatus;
import usecases.INotifierOrderUserCase;
import usecases.IOrderPaymentUseCase;
import usecases.repository.OrderRepository;

public class OrderPaymentUseCaseImpl implements IOrderPaymentUseCase {
  private final INotifierOrderUserCase notifierUseCaseEmail;
  private final INotifierOrderUserCase notifierUseCaseSms;
  private OrderRepository orderRepository;


  public OrderPaymentUseCaseImpl(
          OrderRepository repository,
          INotifierOrderUserCase notifierUseCaseEmail,
          INotifierOrderUserCase notifierUseCaseSms
  ) {
    this.orderRepository = repository;
    this.notifierUseCaseEmail = notifierUseCaseEmail;
    this.notifierUseCaseSms = notifierUseCaseSms;
  }

  @Override
  public void pay(Order order) {
    if (order.getStatus() != OrderStatus.PENDING_PAYMENT) {
      throw new IllegalStateException("O pedido não pode ser pago");
    }

    order.setStatus(OrderStatus.PAID);
    orderRepository.save(order);
    notifierUseCaseEmail.updatedPayOrder(order);
    notifierUseCaseSms.updatedPayOrder(order);
  }
}
