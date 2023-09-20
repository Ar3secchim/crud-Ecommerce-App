package com.crud.ecommerce.app.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class Product implements Serializable, Comparable<Product> {

  private Long id;
  private String description;
  private String barcode;
  private BigDecimal price;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getBarcode() {
    return barcode;
  }

  public void setBarcode(String barcode) {
    this.barcode = barcode;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  @Override
  public int compareTo(Product product) {
    return this.id.compareTo(product.id);
  }
}
