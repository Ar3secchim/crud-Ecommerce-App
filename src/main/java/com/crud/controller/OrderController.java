package com.crud.controller;

import com.crud.controller.dto.OrderItem.OrderItemRequest;
import com.crud.controller.dto.OrderItem.OrderItemResponse;
import com.crud.controller.dto.order.OrderRequest;
import com.crud.controller.dto.order.OrderResponse;
import com.crud.service.CustomerService;
import com.crud.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/order")
public class OrderController {
  @Autowired
  OrderService orderService;
  @Autowired
  CustomerService customerService;

  @Operation(summary = "Create a Order", description = "Returns a Order")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "201", description = "Successfully retrieved"),
          @ApiResponse(responseCode = "400", description = "Not possible create order")
  })
  @PostMapping
  public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest){
    OrderResponse orderResponse = orderService.create(orderRequest);
    return ResponseEntity
            .created(URI.create("/order/"+orderResponse.getId()))
            .body(orderResponse);
  }

  @Operation(summary = "Add items a Order", description = "Returns a Order")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
          @ApiResponse(responseCode = "400", description = "Not possible add item a order")
  })
  @PostMapping("/{id}")
  public ResponseEntity<OrderItemResponse> addItemOrder(
          @PathVariable Integer id,
          @RequestBody OrderItemRequest orderItemRequest
  ){
    OrderItemResponse orderItemResponse = orderService.addItem(id, orderItemRequest);
    return ResponseEntity
            .created(URI.create("/order/"+id))
            .body(orderItemResponse);
  }

  @Operation(summary = "Get all list Order", description = "Returns a list Order")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
          @ApiResponse(responseCode = "400", description = "Not possible list a order")
  })
  @GetMapping
  public ResponseEntity<List<OrderResponse>> listOrder(){
    return ResponseEntity.ok(orderService.findAll());
  }

  @Operation(summary = "Get a Order by id", description = "Returns a Order")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
          @ApiResponse(responseCode = "400", description = "Not possible a order")
  })
  @GetMapping("/{id}")
  public ResponseEntity<OrderResponse> listOrderById(@PathVariable Integer id){
    return ResponseEntity.ok(orderService.findById(id));
  }

  @Operation(summary = "Update Item a Order", description = "Returns a Order")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
          @ApiResponse(responseCode = "400", description = "Not possible update order")
  })
  @PutMapping("/ordemItem/{idOrderItem}")
  public ResponseEntity<OrderItemResponse> updateOrder(
          @PathVariable Integer idOrderItem,
          @RequestBody OrderItemRequest orderItemRequest
  ){
    OrderItemResponse orderItemResponse = orderService.changeAmountItem(idOrderItem, orderItemRequest);

    return ResponseEntity
            .created(URI.create("/order/ordemItem"+idOrderItem))
            .body(orderItemResponse);
  }

  @Operation(summary = "Delete Item a Order", description = "Returns a Order upadate")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
          @ApiResponse(responseCode = "400", description = "Not possible remove item a order")
  })
  @DeleteMapping ("/ordemItem/{idOrderItem}")
  public ResponseEntity<OrderItemResponse> deleteOrderItem(
          @PathVariable Integer idOrderItem
  ){
    orderService.removeItem(idOrderItem);
    return ResponseEntity.noContent().build();
  }

  @Operation(summary = "Delete Order")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
          @ApiResponse(responseCode = "400", description = "Not possible delete order")
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<OrderResponse> deleteOrder(@PathVariable Integer id){
    orderService.deleteOrder(id);
    return ResponseEntity.noContent().build();
  }
}
