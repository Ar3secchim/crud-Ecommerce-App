package usecases.impl;

import model.Order;
import model.OrderStatus;
import usecases.INotifierOrderUserCase;
import usecases.IOrderShippingUseCase;
import usecases.repository.OrderRepository;

public class OrderShippingUseCaseImpl implements IOrderShippingUseCase {

  private final OrderRepository orderRepository;
  private final INotifierOrderUserCase notifierUseCaseEmail;
  private final INotifierOrderUserCase notifierUseCaseSms;


  public OrderShippingUseCaseImpl(
          OrderRepository orderRepository,
          INotifierOrderUserCase notifierUseCaseEmail,
          INotifierOrderUserCase notifierUseCaseSms
  ) {
    this.orderRepository = orderRepository;
    this.notifierUseCaseEmail = notifierUseCaseEmail;
    this.notifierUseCaseSms = notifierUseCaseSms;
  }

  @Override
  public void shipping(Order order) {
    if (order.getStatus() != OrderStatus.PAID) {
      throw new IllegalStateException("O pedido não pode ser pago");
    }

    order.setStatus(OrderStatus.FINISH);
    orderRepository.save(order);
    notifierUseCaseEmail.shipping(order);
    notifierUseCaseSms.shipping(order);
  }

}
