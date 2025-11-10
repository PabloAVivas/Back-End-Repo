package com.food.BackEndRepo.entity.dto.order;

import com.food.BackEndRepo.entity.dto.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderEdit {
    State state;
}
