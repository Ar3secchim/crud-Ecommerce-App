package com.crud.infra.queue.DTO;

import com.crud.modules.product.entity.Product;
import lombok.Data;

@Data
public class StockReservationRequest {
  private String skuId;
  private Product item;
}
