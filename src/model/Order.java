package model;

import java.time.LocalDateTime;
import java.util.List;

public class Order implements Comparable<Order> {

  private Long id;
  private Customer customer;
  private List<OrderItem> items;
  private LocalDateTime orderedAt;
  private OrderStatus status;
  private String shippingAddress;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public List<OrderItem> getItems() {
    return items;
  }

  public void setItems(List<OrderItem> items) {
    this.items = items;
  }

  public LocalDateTime getOrderedAt() {
    return orderedAt;
  }

  public void setOrderedAt(LocalDateTime orderedAt) {
    this.orderedAt = orderedAt;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }

  public String getShippingAddress() {
    return shippingAddress;
  }

  public void setShippingAddress(String shippingAddress) {
    this.shippingAddress = shippingAddress;
  }

  @Override
  public int compareTo(Order order) {
    return this.id.compareTo(order.id);
  }
}
