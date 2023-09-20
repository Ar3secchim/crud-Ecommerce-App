package com.crud.ecommerce.app.application.factory;

import com.crud.ecommerce.app.application.*;
import com.crud.ecommerce.app.application.impl.*;
import com.crud.ecommerce.app.application.integration.database.MemoryDatabase;
import com.crud.ecommerce.app.application.integration.email.OrderEmailNotifierImpl;
import com.crud.ecommerce.app.application.integration.email.SendEmail;
import com.crud.ecommerce.app.application.integration.memoryrepository.OrderEntityMerge;
import com.crud.ecommerce.app.application.integration.memoryrepository.OrderMemoryRepositoryImpl;
import com.crud.ecommerce.app.application.integration.sms.OrderSmsNotifierImpl;
import com.crud.ecommerce.app.application.integration.sms.SendSms;
import com.crud.ecommerce.app.application.repository.OrderRepository;

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
