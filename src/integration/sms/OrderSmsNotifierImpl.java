package integration.sms;

import model.Order;
import usecases.INotifierOrderUserCase;

public class OrderSmsNotifierImpl implements INotifierOrderUserCase {
  private final SendSms sendSms;

  public OrderSmsNotifierImpl(SendSms sendSms) {
    this.sendSms = sendSms;
  }

  @Override
  public void shipping(Order order) {
    sendSms.send("1111111111111", order.getCustomer().getTelephone(), "Seu pedido foi enviado");
  }

  @Override
  public void updatedPayOrder(Order order) {
    sendSms.send("1111111111111", order.getCustomer().getTelephone(), "O pagamento do seu pedido " + order.getId() +
            " mudou o status para " + order.getStatus());
  }

  @Override
  public void pendingPayment(Order order) {
    sendSms.send("1111111111111", order.getCustomer().getTelephone(), "Seu pedido " + order.getId() +
            " está aguardado pagamento");
  }
}
