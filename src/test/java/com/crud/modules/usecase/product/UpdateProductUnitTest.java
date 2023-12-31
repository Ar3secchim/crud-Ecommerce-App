package com.crud.modules.usecase.product;

import com.crud.modules.product.DTO.ProductRequest;
import com.crud.modules.product.DTO.ProductResponse;
import com.crud.modules.product.entity.Product;
import com.crud.modules.product.repository.ProductRepository;
import com.crud.modules.product.usecase.UpdateProduct;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
class UpdateProductUnitTest {
  @Mock
  private ProductRepository repository;
  @InjectMocks
  private UpdateProduct updateProduct;

  @Test
  @DisplayName("Should update product")
  void UpdateProductSuccess() throws Exception {
    ProductRequest productRequest = new ProductRequest();
    productRequest.setQuantityStock(10);
    productRequest.setPrice(BigDecimal.valueOf(250));
    productRequest.setDescription("uni-Test");
    productRequest.setName("uni-Test");

    Product product = new Product();
    product.setSkuId("uni-test");

    when(repository.findProductById("uni-test")).thenReturn(product);

    Product productValid = repository.findProductById("uni-test");

    ProductResponse productResponse = updateProduct.execute(productValid.getSkuId(), productRequest);

    verify(repository, times(1)).save(any());
    assertEquals("uni-Test", productResponse.getName());
  }

  @Test
  @DisplayName("Should update product invalid")
  void UpdateProductIdInvalid(){
    ProductRequest productRequest = new ProductRequest();

    when(repository.findProductById("uni-test")).thenReturn(new Product());

    Product product = repository.findProductById("uni-test");

    Exception exception = assertThrows(
            Exception.class, () -> updateProduct.execute(product.getSkuId(), productRequest));
    assertEquals("Not exist product", exception.getMessage());
  }
}
