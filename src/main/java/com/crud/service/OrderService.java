package com.crud.service;

import com.crud.controller.dto.OrderItem.OrderItemRequest;
import com.crud.controller.dto.OrderItem.OrderItemResponse;
import com.crud.controller.dto.order.OrderRequest;
import com.crud.controller.dto.order.OrderResponse;
import com.crud.model.Customer;
import com.crud.model.Order;
import com.crud.model.OrderItem;
import com.crud.repository.OrdemItemRepository;
import com.crud.usecases.IOrderUseCase;
import com.crud.model.Product;
import com.crud.repository.CustomerRepository;
import com.crud.repository.OrderRepository;
import com.crud.repository.ProductRepository;
import com.crud.utils.OrdemItemConvert;
import com.crud.utils.OrderConvert;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderUseCase {
  @Autowired
  OrderRepository orderRepository;
  @Autowired
  CustomerRepository customerRepository;
  @Autowired
  ProductRepository productRepository;
  @Autowired
  OrdemItemRepository ordemItemRepository;

  @Override
  public OrderResponse create(OrderRequest orderRequest) {
    Customer customer = customerRepository.findById(orderRequest.getCustomer()).get();
    Order order = OrderConvert.toEntity(customer);
    return OrderConvert.toResponseOrder(orderRepository.save(order));
  }

  @Override
  public List<OrderResponse> findAll(){
    return OrderConvert.toResponseList(orderRepository.findAll());
  }

  @Transactional
  public OrderResponse findById(Integer orderId){
    return OrderConvert.toResponseOrder(orderRepository.findById(orderId).get());
  }


  @Override
  public OrderItemResponse addItem(Integer orderId, OrderItemRequest orderItemRequest) {
    Order order = orderRepository.findById(orderId).get();
    Product product = productRepository.findProductById(orderItemRequest.getProduct());

    OrderItem newItem = OrdemItemConvert.toEntity(orderItemRequest, order, product);
    ordemItemRepository.save(newItem);
    order.getOrderItens().add(newItem);

    update(order.getId(), order);
    return OrdemItemConvert.toResponseOrderItem(newItem);
  }

  @Override
  public OrderItem changeAmountItem(Order order, Product product, Integer amount) {
    return null;
  }

  public OrderItemResponse changeAmountItem(Integer orderId, OrderItemRequest orderRequest) {
   //changeAmountItem
    return  null;
  }

  @Override
  public Order findByCustomer(Customer customer) {
    return orderRepository.findByCustomer(customer);
  }

  @Override
  public OrderResponse update(Integer orderId, Order orderRequest) {
    Optional<Order> optionalOrder = orderRepository.findById(orderId);
    Order existingOrder = optionalOrder.get();

    existingOrder.setStatus(orderRequest.getStatus());
    existingOrder.setUpdatedAt(LocalDateTime.now());
    existingOrder.setTotal(orderRequest.getTotal());
    existingOrder.setOrderItens(orderRequest.getOrderItens());

    Order updatedOrder = orderRepository.save(existingOrder);

    return OrderConvert.toResponseOrder(updatedOrder);
  }

  public void removeItem(Integer order, Integer productId) {
    Order inserted = orderRepository.findById(order).get();
    List<OrderItem> listItems = inserted.getOrderItens();
    Product product = productRepository.findProductById(productId);
    Product itemDelete = null;

    for (OrderItem item : listItems) {
      if (item.getProduct() == product) {
        itemDelete =  item.getProduct();
        break;
      }
    }

    if (itemDelete == null) {
      throw new IllegalStateException("Produto não encontrado");
    }

    for (OrderItem item : listItems) {
      if (item.getProduct().equals(itemDelete)) {
        listItems.remove(item);
        break;
      }
    }
    inserted.setOrderItens(listItems);

    update(inserted.getId(), inserted);
  }

  public void deleteOrder(Integer id) {
    if (id != null) {
      orderRepository.deleteById(id);
    }
  }
}
