package com.food.BackEndRepo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class OrderDetail extends Base {
    private int amount;
    private double subtotal;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
