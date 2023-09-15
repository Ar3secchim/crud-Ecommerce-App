package integration.memoryrepository;

import integration.database.MemoryDatabase;
import model.Customer;
import model.Order;
import model.OrderItem;
import usecases.repository.OrderRepository;
import usecases.repository.RepositoryException;

import java.util.List;
import java.util.Objects;

public class OrderMemoryRepositoryImpl implements OrderRepository {

  private final MemoryDatabase database;
  private final OrderEntityMerge entityMerge;

  public OrderMemoryRepositoryImpl(MemoryDatabase database, OrderEntityMerge entityMerge) {
    this.database = database;
    this.entityMerge = entityMerge;
  }

  @Override
  public void save(Order order) throws RepositoryException {
    order.setId(database.nextId());
    entityMerge.merge(order);
    for (OrderItem item : order.getItems()) {
      entityMerge.merge(item);
    }
    database.saveOrUpdate(order);
  }

  @Override
  public List<Order> listAll() throws RepositoryException {
    return database.listAll(Order.class);
  }

  @Override
  public Order findById(Long id) throws RepositoryException {
    return database.find(
            Order.class,
            it -> Objects.equals(id, it.getId())
    ).stream().findFirst().orElse(null);
  }

  @Override
  public void update(Order order) throws RepositoryException {
    entityMerge.merge(order);
    for (OrderItem item : order.getItems()) {
      entityMerge.merge(item);
    }
    Order inserted = findById(order.getId());
    inserted.setCustomer(order.getCustomer());
    inserted.setStatus(order.getStatus());
    inserted.setShippingAddress(order.getShippingAddress());
    inserted.setItems(order.getItems());
    database.saveOrUpdate(inserted);
  }

  @Override
  public Order delete(Order object) throws RepositoryException {
    return database.delete(object);
  }

  @Override
  public List<Order> findByCustomer(Customer customer) {
    return database.find(
            Order.class,
            it -> Objects.equals(customer.getId(), it.getCustomer().getId())
    );
  }
}
