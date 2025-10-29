package com.food.BackEndRepo.entity.dto.product;

import com.food.BackEndRepo.entity.dto.category.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    Long id;
    String name;
    String description;
    double price;
    CategoryDto category;
    String url;
}
