package integration.memoryrepository;

import integration.database.MemoryDatabase;
import model.Customer;
import model.Order;
import model.OrderItem;
import model.Product;

import java.util.Objects;

public class OrderEntityMerge {
  private final MemoryDatabase database;

  public OrderEntityMerge(MemoryDatabase database) {
    this.database = database;
  }

  public void merge(Order order) {
    Customer customerRegistered = findCustomer(order.getCustomer().getId());
    order.setCustomer(customerRegistered);
  }

  private Customer findCustomer(Long id) {
    Customer found = database.find(
            Customer.class,
            it -> Objects.equals(id, it.getId())
    ).stream().findFirst().orElse(null);

    return found;
  }

  public void merge(OrderItem item) {
    Product productRegistered = findProduct(item.getProduct().getId());
    item.setProduct(productRegistered);
  }

  private Product findProduct(Long id) {
    Product found = database.find(
            Product.class,
            it -> Objects.equals(id, it.getId())
    ).stream().findFirst().orElse(null);
    return found;
  }
}
