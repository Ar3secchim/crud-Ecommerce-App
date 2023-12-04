package com.crud.modules.product.usecase;

import com.crud.modules.product.DTO.ProductRequest;
import com.crud.modules.product.DTO.ProductResponse;
import com.crud.modules.product.entity.Product;
import com.crud.modules.product.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
    productRequest.setPrice(250.00);
    productRequest.setDescription("uni-Test");
    productRequest.setName("uni-Test");

    Product product = new Product();
    product.setSku("uni-test");

    when(repository.findProductById("uni-test")).thenReturn(product);

    Product productValid = repository.findProductById("uni-test");

    ProductResponse productResponse = updateProduct.execute(productValid.getSku(), productRequest);

    verify(repository, times(1)).save(any());
    assertEquals("uni-Test", productResponse.getName());
  }

  @Test
  @DisplayName("Should update product invalid")
  void UpdateProductIdInvalid() throws Exception {
    ProductRequest productRequest = new ProductRequest();

    when(repository.findProductById("uni-test")).thenReturn(new Product());

    Product product = repository.findProductById("uni-test");

    Exception exception = assertThrows(
            Exception.class, () -> updateProduct.execute(product.getSku(), productRequest));
    assertEquals("Not exist product", exception.getMessage());
  }
}
