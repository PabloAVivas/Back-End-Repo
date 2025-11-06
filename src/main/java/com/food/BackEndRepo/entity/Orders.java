package com.food.BackEndRepo.entity;

import com.food.BackEndRepo.entity.dto.enums.State;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Orders extends Base {
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private State state;

    private double total;

    @OneToMany
    @JoinColumn(name = "order_id")
    @Builder.Default
    private List<OrderDetail> details = new ArrayList<>();

    public void addOrderDetail(OrderDetail od){
        details.add(od);
        total = total + od.getSubtotal();
    }
}
