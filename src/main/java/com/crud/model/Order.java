package com.crud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "orders")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "customers_id")
  private Customer customer;

  @ManyToMany
  @JoinTable(
          name = "order_order_item", // Nome da tabela de junção
          joinColumns = @JoinColumn(name = "order_id"), // Nome da coluna da tabela "Orders"
          inverseJoinColumns = @JoinColumn(name = "order_item_id") // Nome da coluna da tabela "OrderItem"
  )
  private List<OrderItem> items;

  @Column(name = "ordered_at")
  private LocalDateTime orderedAt;

  @Column
  private OrderStatus status;

  @Column(name ="shipping_address")
  private String shippingAddress;
}
