package com.crud.modules.integration.product.repository;

import com.crud.modules.product.entity.Product;
import com.crud.modules.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ProductRepositoryIntegrationTest {
  @Autowired
  private TestEntityManager testEntityManager;

  @Autowired
  private ProductRepository productRepository;

  @Test
  public void ShouldFindProductById(){
    Product product= new Product();
    product.setSkuId(UUID.randomUUID().toString());
    product.setQuantityStock(10);
    product.setPrice(BigDecimal.valueOf(250));
    product.setDescription("uni-Test");
    product.setName("uni-test");

    testEntityManager.persist(product);

    Product productFound =
            productRepository.findProductById(product.getSkuId());

    assertEquals(product.getName(), productFound.getName());
    assertEquals(product.getPrice(), productFound.getPrice());
    assertEquals(product.getDescription(), productFound.getDescription());
    assertEquals(product.getQuantityStock(), productFound.getQuantityStock());

  }
}
