package integration.email;

import model.Order;
import usecases.INotifierOrderUserCase;

public class OrderEmailNotifierImpl implements INotifierOrderUserCase {

  private SendEmail sendEmail;

  public OrderEmailNotifierImpl(SendEmail sendEmail) {
    this.sendEmail = sendEmail;
  }

  @Override
  public void shipping(Order order) {
    sendEmail.send("comunicado@ada.com.br", order.getCustomer().getEmail(), " Seu pedido foi enviado");
  }

  @Override
  public void updatedPayOrder(Order order) {
    sendEmail.send("comunicado@ada.com.br", order.getCustomer().getEmail(),
            "O pagamento do seu pedido " + order.getId() +
                    " mudou o status para " + order.getStatus());
  }

  @Override
  public void pendingPayment(Order order) {
    sendEmail.send("comunicado@ada.com.br", order.getCustomer().getEmail(),
            "Seu pedido " + order.getId() +
                    " está aguardando pagamento " + order.getStatus());
  }
}
