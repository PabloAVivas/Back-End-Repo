package com.food.BackEndRepo.entity;

import com.food.BackEndRepo.entity.dto.enums.State;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Orders extends Base {
    private LocalDate date;
    private State state;
    private double total;
}
