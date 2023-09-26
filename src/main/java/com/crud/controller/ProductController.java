package com.crud.controller;

import com.crud.controller.dto.product.ProductRequest;
import com.crud.controller.dto.product.ProductResponse;
import com.crud.model.Product;
import com.crud.service.ProductService;
import com.crud.utils.ProductConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
  @Autowired
  ProductService productService;

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

  @DeleteMapping("/product/{id}")
  public ProductResponse deleteProductById(@PathVariable Integer id){
   Product product = productService.deleteProductById(id);
    return ProductConvert.toResponse(product);
  }

}
