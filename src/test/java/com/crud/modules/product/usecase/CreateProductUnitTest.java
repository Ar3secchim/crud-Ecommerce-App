package com.crud.modules.product.usecase;

import com.crud.infra.exception.ValidationError;
import com.crud.modules.product.DTO.ProductRequest;
import com.crud.modules.product.DTO.ProductResponse;
import com.crud.modules.product.entity.Product;
import com.crud.modules.product.repository.ProductRepository;
import com.crud.utils.ProductConvert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class CreateProductUnitTest {
  @Mock
  private  ProductRepository repository;
  @InjectMocks
  private  CreateProduct createProduct;

  @Test
  @DisplayName("should create product when everything success")
  public void createProductWithSuccess() throws Exception {
    ProductRequest productRequest = new ProductRequest();
    productRequest.setSku(UUID.randomUUID().toString());
    productRequest.setQuantityStock(10);
    productRequest.setPrice(250.00);
    productRequest.setDescription("uni-Test");
    productRequest.setName("uni-test");

    Product product  = ProductConvert.toEntity(productRequest);

    when(repository.save(product)).thenReturn(product);

    ProductResponse productResponse = createProduct.execute(productRequest);

    verify(repository, times(1)).save(any());
    assertEquals(product.getName(), productResponse.getName());
  }

  @Test
  @DisplayName("should exception create product invalid ")
  public void createProductInvalid(){
    Product product  = ProductConvert.toEntity(new ProductRequest());
    when(repository.save(product)).thenReturn(null);
    assertThrows(Exception.class, () -> createProduct.execute(new ProductRequest()));
  }
}
