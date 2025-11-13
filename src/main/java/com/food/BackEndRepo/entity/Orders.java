package com.food.BackEndRepo.entity;

import com.food.BackEndRepo.entity.dto.enums.Delivery;
import com.food.BackEndRepo.entity.dto.enums.Payment;
import com.food.BackEndRepo.entity.dto.enums.State;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Orders extends Base {
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private State state;

    private double total;

    @Enumerated(EnumType.STRING)
    private Payment payment;

    @Enumerated(EnumType.STRING)
    private Delivery delivery;

    @OneToMany(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "order_id")
    @Builder.Default
    private List<OrderDetail> details = new ArrayList<>();

    public void addOrderDetail(OrderDetail od){
        details.add(od);
        total = total + od.getSubtotal();
    }
}
