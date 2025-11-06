package com.food.BackEndRepo.entity.dto.orderDetail;

import com.food.BackEndRepo.entity.Product;

public record OrderDetailCreate (
        int amount,
        double subtotal,
        Long productId
) {
}
