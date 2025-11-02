package com.food.BackEndRepo.entity.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEdit {
    String name;
    String description;
    double price;
    int stock;
    Long categoryId;
    String urlImg;
    boolean availableProduct;
}
