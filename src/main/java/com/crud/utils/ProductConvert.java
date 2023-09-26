package com.crud.utils;

import com.crud.controller.dto.product.ProductRequest;
import com.crud.controller.dto.product.ProductResponse;
import com.crud.model.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductConvert {

  public static Product toEntity(ProductRequest productRequest){
    Product product = new Product();

    product.setId(productRequest.getId());
    product.setDescription(productRequest.getDescription());
    product.setBarcode(productRequest.getBarcode());
    product.setPrice(productRequest.getPrice());
    return product;
  }

  public static ProductResponse toResponse(Product productRequest){
    ProductResponse productResponse = new ProductResponse();

    productResponse.setId(productRequest.getId());
    productResponse.setBarcode(productRequest.getBarcode());
    productResponse.setPrice(productRequest.getPrice());
    return productResponse;
  }

  public static List<ProductResponse> toListResponse(List<Product> listProduct){
    return listProduct.stream()
            .map(ProductConvert::toResponse)
            .collect(Collectors.toList());
  }
}
