package com.food.BackEndRepo.entity.dto.order;

import com.food.BackEndRepo.entity.dto.enums.Delivery;
import com.food.BackEndRepo.entity.dto.enums.Payment;
import com.food.BackEndRepo.entity.dto.enums.State;
import com.food.BackEndRepo.entity.dto.orderDetail.OrderDetailDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    Long id;
    LocalDate date;
    State state;
    Payment payment;
    Delivery delivery;
    double total;
    List<OrderDetailDto> details;
}
