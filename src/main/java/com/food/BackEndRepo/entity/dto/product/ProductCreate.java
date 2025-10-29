package com.food.BackEndRepo.entity.dto.product;

public record ProductCreate(
        String name,
        String description,
        double price,
        Long categoryId,
        String url
) {
}
