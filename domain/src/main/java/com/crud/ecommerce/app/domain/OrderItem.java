package com.crud.ecommerce.app.domain;

import java.math.BigDecimal;

public class OrderItem {

  private Long id;
  private Product product;
  private BigDecimal saleValue;
  private Integer amount;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public BigDecimal getSaleValue() {
    return saleValue;
  }

  public void setSaleValue(BigDecimal saleValue) {
    this.saleValue = saleValue;
  }

  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }
}
