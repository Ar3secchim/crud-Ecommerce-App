package com.crud.modules.usecase.product;

import com.crud.infra.exception.BadRequestClient;
import com.crud.modules.product.DTO.ProductResponse;
import com.crud.modules.product.entity.Product;
import com.crud.modules.product.repository.ProductRepository;
import com.crud.modules.product.usecase.FindProduct;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class FindProductUnitTest {
  @Mock
  private ProductRepository repository;
  @InjectMocks
  private FindProduct findProduct;


  @Test
  @DisplayName("should all product")
  void ListAllProduct(){
    ArrayList<Product> listProducts = new ArrayList<>();
    Product productTest = new Product();

    for (int i = 0; i < 4; i++) {
      productTest.setSkuId("unit-test" +i);
      productTest.setQuantityStock(i);
      productTest.setPrice(BigDecimal.valueOf(i));
      productTest.setDescription("uni-Test " + i);
      productTest.setName("uni-test");
      listProducts.add(productTest);
    }

    when(repository.findAll()).thenReturn(listProducts);

    List<ProductResponse> listAllProduct = findProduct.listAll();

    verify(repository, times(1)).findAll();
    assertEquals(4, listAllProduct.size());

  }

  @Test
  @DisplayName("Should product find by id")
  public void findProductById() throws BadRequestClient {
    Product product = new Product();
    product.setSkuId("uni-Test");

    when(repository.findProductById(product.getSkuId())).thenReturn(product);

    ProductResponse productResponse = findProduct.findById("uni-Test");

    verify(repository, times(1)).findProductById(any());
    assertEquals("uni-Test", productResponse.getSkuId());
  }

  @Test
  @DisplayName("Should product find by id invalid")
  public void findProductByIdInvalid(){
    when(repository.findProductById("unit-test")).thenReturn(null);

    Exception exception = assertThrows(Exception.class,
            () -> findProduct.findById("unit-test"));

    verify(repository, times(1)).findProductById(any());
    assertEquals("Product not found with ID: " + "unit-test", exception.getMessage());
  }
}
