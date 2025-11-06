package com.food.BackEndRepo.entity.dto.order;

import com.food.BackEndRepo.entity.OrderDetail;
import com.food.BackEndRepo.entity.dto.enums.State;

import java.time.LocalDate;
import java.util.List;

public record OrderCreate (
        LocalDate date,
        State state,
        double total,
        List<OrderDetail>details
){
}
