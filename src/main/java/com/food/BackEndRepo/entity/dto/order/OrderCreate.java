package com.food.BackEndRepo.entity.dto.order;
import com.food.BackEndRepo.entity.dto.orderDetail.OrderDetailCreate;
import java.util.List;

public record OrderCreate (
        double total,
        List<OrderDetailCreate>details
){
}
