package com.food.BackEndRepo.entity.dto.order;
import com.food.BackEndRepo.entity.dto.enums.Delivery;
import com.food.BackEndRepo.entity.dto.enums.Payment;
import com.food.BackEndRepo.entity.dto.orderDetail.OrderDetailCreate;
import java.util.List;

public record OrderCreate (
        double total,
        Payment payment,
        Delivery delivery,
        List<OrderDetailCreate>details
){
}
