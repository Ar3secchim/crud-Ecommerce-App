package com.crud.controller;

import com.crud.controller.dto.product.ProductRequest;
import com.crud.controller.dto.product.ProductResponse;
import com.crud.model.Product;
import com.crud.usecases.impl.ProductUseCaseImpl;
import com.crud.utils.ProductConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
  @Autowired
  ProductUseCaseImpl productService;

  @PostMapping ("/product")
  public ProductResponse createCustomer(@RequestBody ProductRequest productRequest){
    Product product =  productService.create(ProductConvert.toEntity(productRequest));
    return ProductConvert.toResponse(product);
  }

  @GetMapping("/product")
  public List<ProductResponse> getProduct(){
    List<Product> listProduct = productService.listAll();
    return ProductConvert.toListResponse(listProduct);
  }

}
