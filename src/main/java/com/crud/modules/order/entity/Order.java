package com.crud.modules.order.entity;

import com.crud.modules.orderItem.entity.OrderItem;
import com.crud.modules.customers.entity.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Data
@Entity(name = "order")
@Table(name = "orders")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false, name = "id_transaction")
  private String idTransaction;

  @ManyToOne
  @JoinColumn(name = "customer_id", nullable = false)
  private Customer customer;

  @OneToMany(
    fetch = FetchType.EAGER,//carregar itens associados obs: desempenho
    mappedBy = "order" //associação bidirecional
  )
  private List<OrderItem> orderItens;

  @Column(nullable = false)
  private BigDecimal total;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private OrderStatus status;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;

  public enum OrderStatus {
      OPEN, PENDING_PAYMENT, PAID, SHIPPING, FINISH;
  }
}
