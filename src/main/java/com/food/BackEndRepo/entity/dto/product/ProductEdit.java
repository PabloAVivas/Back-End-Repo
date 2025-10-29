package com.food.BackEndRepo.entity.dto.product;

import com.food.BackEndRepo.entity.Category;
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
    String url;
}
