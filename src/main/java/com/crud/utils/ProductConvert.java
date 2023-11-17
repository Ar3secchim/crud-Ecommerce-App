package com.crud.utils;

import com.crud.modules.product.DTO.ProductRequest;
import com.crud.modules.product.DTO.ProductResponse;
import com.crud.modules.product.entity.Product;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ProductConvert {

  public static Product toEntity(ProductRequest productRequest){
    Product product = new Product();

    product.setSku(UUID.randomUUID().toString());
    product.setDescription(productRequest.getDescription());
    product.setName(productRequest.getName());
    product.setQuantityStock(product.getQuantityStock());
    product.setPrice(productRequest.getPrice());
    return product;
  }

  public static ProductResponse toResponse(Product product){
    ProductResponse productResponse = new ProductResponse();

    productResponse.setSku(product.getSku());
    productResponse.setName(product.getName());
    productResponse.setPrice(product.getPrice());
    productResponse.setQuantityStock(product.getQuantityStock());
    return productResponse;
  }

  public static List<ProductResponse> toListResponse(List<Product> listProduct){
    return listProduct.stream()
            .map(ProductConvert::toResponse)
            .collect(Collectors.toList());
  }
}
