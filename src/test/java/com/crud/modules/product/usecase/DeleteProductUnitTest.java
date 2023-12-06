package com.crud.modules.product.usecase;

import com.crud.modules.product.entity.Product;
import com.crud.modules.product.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
class DeleteProductUnitTest {
  @Mock
  private ProductRepository repository;

  @InjectMocks
  private  DeleteProduct deleteProduct;

  @Test
  @DisplayName("Should exception a delete customer not exist")
  public void deleteCustomerEquals(){
    when(repository.findProductById("uni-test")).thenReturn(new Product());

    Exception exception = assertThrows(
            Exception.class, () -> deleteProduct.execute("unit-test"));

    assertEquals("Product not found ", exception.getMessage());
  }

  @Test
  @DisplayName("Should exception a delete customer")
  public void deleteCustomer() throws Exception {
    Product product = new Product();
    product.setSku("unit-test");

    when(repository.findProductById("uni-test")).thenReturn(product);

    deleteProduct.execute("uni-test");

    verify(repository, times(1)).delete(any());
  }
}
