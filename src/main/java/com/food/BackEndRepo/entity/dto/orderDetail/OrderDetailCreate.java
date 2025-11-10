package com.food.BackEndRepo.entity.dto.orderDetail;

public record OrderDetailCreate (
        int amount,
        double subtotal,
        Long productId
) {
}
