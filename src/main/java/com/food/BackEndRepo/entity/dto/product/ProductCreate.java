package com.food.BackEndRepo.entity.dto.product;

public record ProductCreate(
        String name,
        String description,
        double price,
        int stock,
        Long categoryId,
        String urlImg,
        boolean availableProduct
) {
}
