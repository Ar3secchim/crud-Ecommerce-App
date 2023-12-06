package com.crud.modules.orderItem.usecase;
;
import com.crud.modules.orderItem.entity.OrderItem;
import com.crud.modules.orderItem.repository.OrdemItemRepository;
import com.crud.modules.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteItemOrder{
  @Autowired
  OrdemItemRepository ordemItemRepository;

  public void execute(String id) throws Exception {
    OrderItem orderItem = ordemItemRepository.findOrderItemById(id);

    if(orderItem == null) throw  new Exception("Order not found");

    ordemItemRepository.delete(orderItem);
  }
}
