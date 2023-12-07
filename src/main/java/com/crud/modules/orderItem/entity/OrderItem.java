package com.crud.modules.orderItem.entity;

import com.crud.modules.product.entity.Product;
import com.crud.modules.order.entity.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity(name = "OrderItem")
@Table(name = "Order_item")
public class OrderItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false, name = "id_transaction")
  private String idTransaction;

  @ManyToOne
  @JoinColumn(name = "order_id")
  private Order order;

  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  @Column(nullable = false)
  private Integer amount;

  @Column(nullable = false)
  private BigDecimal price;

  @Column(nullable = false)
  private BigDecimal total;
}
