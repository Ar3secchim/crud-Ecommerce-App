package com.crud.modules.usecase.product;

import com.crud.modules.product.DTO.ProductRequest;
import com.crud.modules.product.DTO.ProductResponse;
import com.crud.modules.product.entity.Product;
import com.crud.modules.product.repository.ProductRepository;
import com.crud.modules.product.usecase.CreateProduct;
import com.crud.utils.ProductConvert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
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
  private CreateProduct createProduct;

  private ProductRequest productRequest;
  private Product product;

  @BeforeEach
  public void setup(){
    productRequest = new ProductRequest();
    productRequest.setSkuId(UUID.randomUUID().toString());
    productRequest.setQuantityStock(10);
    productRequest.setPrice(BigDecimal.valueOf(250));
    productRequest.setDescription("uni-Test");
    productRequest.setName("uni-test");

    product  = ProductConvert.toEntity(productRequest);
  }

  @Test
  @DisplayName("should create product when everything success")
  public void createProductWithSuccess() throws Exception {
    when(repository.save(product)).thenReturn(product);

    ProductResponse productResponse = createProduct.execute(productRequest);

    verify(repository, times(1)).save(any());
    assertEquals(product.getName(), productResponse.getName());
  }

  @Test
  @DisplayName("should exception create product name Invalid ")
  public void createProductNameInvalid(){
    productRequest.setName(null);

    Exception exception = assertThrows(Exception.class,
            () -> createProduct.execute(productRequest));

    assertEquals("Name is required", exception.getMessage());
  }

  @Test
  @DisplayName("should exception create product price Invalid ")
  public void createProductPriceInvalid(){
    productRequest.setPrice(null);

    Exception exception =  assertThrows(Exception.class,
            () -> createProduct.execute(productRequest));

    assertEquals("Price is required", exception.getMessage());
  }

  @Test
  @DisplayName("should exception create product quantity Invalid ")
  public void createProductQuantityInvalid(){
    productRequest.setQuantityStock(null);

    Exception exception =  assertThrows(Exception.class,
            () -> createProduct.execute(productRequest));

    assertEquals("Quantity is required", exception.getMessage());
  }
}
