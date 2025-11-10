package com.food.BackEndRepo.entity.dto.orderDetail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailEdit {
    int amount;
    double subtotal;
    Long productId;
}
