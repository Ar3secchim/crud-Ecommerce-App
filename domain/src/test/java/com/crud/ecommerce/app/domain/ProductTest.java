package com.crud.ecommerce.app.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductTest {
  @Test
  public void  testNewCategory(){
    Assertions.assertNotNull(new Product());
  }
}
