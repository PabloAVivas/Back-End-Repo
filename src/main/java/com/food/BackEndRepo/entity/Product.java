package com.food.BackEndRepo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Product extends Base {
    private String name;
    private String description;
    private double price;
    private int stock;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String urlImg;
    private boolean availableProduct;

    public boolean checkStock (int amount){
        return stock>=amount;
    }

    public void subtractStock (int amount){
        stock = stock-amount;
    }
}
