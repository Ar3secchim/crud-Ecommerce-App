package factory;

import integration.database.MemoryDatabase;
import integration.email.OrderEmailNotifierImpl;
import integration.email.SendEmail;
import integration.memoryrepository.OrderEntityMerge;
import integration.memoryrepository.OrderMemoryRepositoryImpl;
import integration.sms.OrderSmsNotifierImpl;
import integration.sms.SendSms;
import usecases.*;
import usecases.impl.*;
import usecases.repository.OrderRepository;

public class OrderFactory {
  public static ICreateOrderUseCase createUseCase() {
    return new OrderCreateUseCaseImpl(
            createRepository(),
            CustomerFactory.createRepository()
    );
  }

  public static IOrderAddItemUseCase orderAddItemUseCase() {
    return new OrderAddItemUseCaseImpl(
            createRepository()
    );
  }

  public static IOrderChangeItemUseCase orderChangeItemUseCase() {
    return new OrderChangeItemUseCaseImpl(
            createRepository()
    );
  }

  public static IOrderRemoveItemUseCase orderRemoveItemUseCase() {
    return new OrderRemoveItemUseImpl(
            createRepository()
    );
  }

  public static IOrderPlaceUseCase placeOrderUseCase() {
    return new OrderPlaceUseCaseImpl(
            createRepository(),
            OrderFactory.createNotifierOrderSendEmail(),
            OrderFactory.createNotifierOrderSendSms()
    );
  }

  public static IOrderPaymentUseCase payOrderUseCase() {
    return new OrderPaymentUseCaseImpl(
            createRepository(),
            OrderFactory.createNotifierOrderSendEmail(),
            OrderFactory.createNotifierOrderSendSms()
    );
  }

  public static IOrderShippingUseCase shippingUseCase() {
    return new OrderShippingUseCaseImpl(
            createRepository(),
            OrderFactory.createNotifierOrderSendEmail(),
            OrderFactory.createNotifierOrderSendSms()
    );
  }

  public static OrderRepository createRepository() {
    return new OrderMemoryRepositoryImpl(
            MemoryDatabase.getInstance(),
            new OrderEntityMerge(MemoryDatabase.getInstance())
    );
  }

  public static INotifierOrderUserCase createNotifierOrderSendEmail() {
    return new OrderEmailNotifierImpl(new SendEmail());
  }

  public static INotifierOrderUserCase createNotifierOrderSendSms() {
    return new OrderSmsNotifierImpl(new SendSms());
  }
}
