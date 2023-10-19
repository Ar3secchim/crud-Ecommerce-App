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
import jakarta.persistence.EntityNotFoundException;
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
    order.setTotal(calculateNewTotal(order));

    update(order.getId(), order);
    return OrdemItemConvert.toResponseOrderItem(newItem);
  }

  @Override
  public OrderItemResponse changeAmountItem(Integer orderItemID, OrderItemRequest orderItemRequest) {
    Optional<OrderItem> optionalOrderItem = ordemItemRepository.findById(orderItemID);

    if (optionalOrderItem.isPresent()) {
      OrderItem orderItem = optionalOrderItem.get();
      Product product = productRepository.findProductById(orderItemRequest.getProduct());

      orderItem.setAmount(orderItemRequest.getAmount());
      orderItem.setTotal(orderItemRequest.getAmount() * product.getPrice());

      ordemItemRepository.save(orderItem);

      Order order = orderItem.getOrder();
      order.setTotal(calculateNewTotal(order));

      orderRepository.save(order);

      return OrdemItemConvert.toResponseOrderItem(orderItem);
    } else {
      throw new EntityNotFoundException("Item de pedido não encontrado com ID: " + orderItemID);
    }
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

  public void removeItem(Integer orderItem) {
    OrderItem inserted = ordemItemRepository.findById(orderItem).get();
    Order order = ordemItemRepository.findById(orderItem).get().getOrder();

    Product items = inserted.getProduct();
    Product product = productRepository.findProductById(items.getId());

    List<OrderItem> listItems = order.getOrderItens();

    if (product == null) {
      throw new IllegalStateException("Produto não encontrado");
    }

    for (OrderItem item : listItems) {
      if (item.getProduct().equals(items)) {
        listItems.remove(0);
        break;
      }
    }

    order.setOrderItens(listItems);

    update(order.getId(), order);
  }

  public void deleteOrder(Integer id) {
    if (id != null) {
      orderRepository.deleteById(id);
    }
  }

  private Double calculateNewTotal(Order order) {
    List<OrderItem> orderItems = order.getOrderItens();
    double newTotal = 0.0;

    for (OrderItem item : orderItems) {
      Double itemTotal = item.getTotal();
      newTotal = newTotal + itemTotal;
    }

    return newTotal;
  }
}
