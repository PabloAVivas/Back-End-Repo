package com.food.BackEndRepo.entity.dto.orderDetail;

import com.food.BackEndRepo.entity.dto.product.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDto {
    Long id;
    int amount;
    double subtotal;
    ProductDto product;
}
