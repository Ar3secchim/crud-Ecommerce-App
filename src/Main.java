import factory.CustomerFactory;
import factory.OrderFactory;
import factory.ProductFactory;
import model.Customer;
import model.Order;
import model.Product;
import usecases.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;

public class Main {
  public static void main(String[] args) {
    IProductUseCase productUseCase = ProductFactory.createUseCase();
    ICustomerUseCase customerUseCase = CustomerFactory.createUseCase();
    ICreateOrderUseCase orderUseCase = OrderFactory.createUseCase();
    IOrderAddItemUseCase orderAddItemUseCase = OrderFactory.orderAddItemUseCase();
    IOrderRemoveItemUseCase orderRemoveItemUseCase = OrderFactory.orderRemoveItemUseCase();
    IOrderChangeItemUseCase orderChangeItemUseCase = OrderFactory.orderChangeItemUseCase();
    IOrderPlaceUseCase orderPlaceUseCase = OrderFactory.placeOrderUseCase();
    IOrderPaymentUseCase orderPayUseCase = OrderFactory.payOrderUseCase();
    IOrderShippingUseCase orderShipping = OrderFactory.shippingUseCase();

    Customer customer = new Customer();
    customer.setName("Renara");
    customer.setEmail(Collections.singletonList("renarasecchim@gmail.com"));
    customer.setTelephone(Collections.singletonList("91981695389"));
    customer.setBirthDate(LocalDate.ofEpochDay(4 / 2 / 1997));
    customerUseCase.create(customer);

    Product productOne = new Product();
    productOne.setDescription("023");
    productUseCase.create(productOne);

    Product productTwo = new Product();
    productTwo.setDescription("1546");
    productUseCase.create(productTwo);

    Product productThree = new Product();
    productThree.setDescription("516");
    productUseCase.create(productThree);

    Order order = orderUseCase.create(customer);
    orderAddItemUseCase.addItem(order, productOne, BigDecimal.TEN, 1);
    orderAddItemUseCase.addItem(order, productTwo, BigDecimal.TEN, 2);
    orderChangeItemUseCase.changeAmount(order, productTwo, 5);
    orderRemoveItemUseCase.removeItem(order, productOne);
    orderPlaceUseCase.placeOrder(order);
    orderPayUseCase.pay(order);
    orderShipping.shipping(order);
  }
}
