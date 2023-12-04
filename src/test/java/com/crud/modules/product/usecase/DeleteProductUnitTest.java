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
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
class DeleteProductUnitTest {
  @Mock
  private ProductRepository repository;

  @InjectMocks
  private  DeleteProduct deleteProduct;

  @Test
  @DisplayName("Should exception a delete customer not exist")
  public void deleteCustomerEquals(){
    when(repository.findById("uni-test")).thenReturn(Optional.of(new Product()));

    Exception exception = assertThrows(
            Exception.class, () -> deleteProduct.execute("unit-test"));

    assertEquals("Produto não encontrado", exception.getMessage());
  }
}
