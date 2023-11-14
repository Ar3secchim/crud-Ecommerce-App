package com.crud.modules.order.controller;

import com.crud.modules.order.usecase.impl.CreateOrderUseCaseImpl;
import com.crud.modules.order.usecase.impl.FindOrderUseCaseImpl;
import com.crud.modules.orderItem.DTO.OrderItemRequest;
import com.crud.modules.orderItem.DTO.OrderItemResponse;
import com.crud.modules.order.DTO.OrderRequest;
import com.crud.modules.order.DTO.OrderResponse;
import com.crud.modules.order.usecase.impl.DeleteOrderUseCaseImpl;
import com.crud.modules.orderItem.usecase.impl.AddItemOrderItemUseCaseImpl;
import com.crud.modules.orderItem.usecase.impl.ChangeAmountItemUseCaseImpl;
import com.crud.modules.orderItem.usecase.impl.DeleteItemOrderUseCaseImpl;
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
  CreateOrderUseCaseImpl createOrderService;
  @Autowired
  DeleteOrderUseCaseImpl deleteOrderService;
  @Autowired
  FindOrderUseCaseImpl findOrderService;

  @Autowired
  DeleteItemOrderUseCaseImpl deleteItemOrderService;
  @Autowired
  AddItemOrderItemUseCaseImpl addItemOrderService;
  @Autowired
  ChangeAmountItemUseCaseImpl changeAmountItemService;


  @Operation(summary = "Create a Order", description = "Returns a Order")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "201", description = "Successfully retrieved"),
          @ApiResponse(responseCode = "400", description = "Not possible create order")
  })
  @PostMapping
  public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest){
    OrderResponse orderResponse = createOrderService.create(orderRequest);
    return ResponseEntity
            .created(URI.create("/order/"+orderResponse.getSku()))
            .body(orderResponse);
  }

  @Operation(summary = "Add items a Order", description = "Returns a Order")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
          @ApiResponse(responseCode = "400", description = "Not possible add item a order")
  })
  @PostMapping("/{id}")
  public ResponseEntity<OrderItemResponse> addItemOrder(
          @PathVariable String id,
          @RequestBody OrderItemRequest orderItemRequest
  ){
    OrderItemResponse orderItemResponse = addItemOrderService.addItem(id, orderItemRequest);
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
    return ResponseEntity.ok(findOrderService.findAll());
  }

  @Operation(summary = "Get a Order by id", description = "Returns a Order")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
          @ApiResponse(responseCode = "400", description = "Not possible a order")
  })
  @GetMapping("/{id}")
  public ResponseEntity<OrderResponse> listOrderById(@PathVariable String id){
    return ResponseEntity.ok(findOrderService.findById(id));
  }

  @Operation(summary = "Update Item a Order", description = "Returns a Order")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
          @ApiResponse(responseCode = "400", description = "Not possible update order")
  })
  @PutMapping("/ordemItem/{idOrderItem}")
  public ResponseEntity<OrderItemResponse> updateOrder(
          @PathVariable String idOrderItem,
          @RequestBody OrderItemRequest orderItemRequest
  ){
    OrderItemResponse orderItemResponse = changeAmountItemService.changeAmountItem(idOrderItem, orderItemRequest);

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
          @PathVariable String idOrderItem
  ){
    deleteItemOrderService.deleteItem(idOrderItem);
    return ResponseEntity.noContent().build();
  }

  @Operation(summary = "Delete Order")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
          @ApiResponse(responseCode = "400", description = "Not possible delete order")
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<OrderResponse> deleteOrder(@PathVariable String id){
    deleteOrderService.deleteOrder(id);
    return ResponseEntity.noContent().build();
  }
}
