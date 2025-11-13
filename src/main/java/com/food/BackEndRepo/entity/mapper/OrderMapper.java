package com.food.BackEndRepo.entity.mapper;

import com.food.BackEndRepo.entity.Orders;
import com.food.BackEndRepo.entity.dto.order.OrderCreate;
import com.food.BackEndRepo.entity.dto.order.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.food.BackEndRepo.entity.dto.enums.State.PENDING;

@Component
public class OrderMapper {

    @Autowired
    OrderDetailMapper orderDetailMapper;

    public Orders toEntity (OrderCreate orderC){
        Orders order = new Orders();
        order.setDate(LocalDateTime.now());
        order.setState(PENDING);
        order.setPayment(orderC.payment());
        order.setDelivery(orderC.delivery());
        order.setTotal(orderC.total());
        order.setDetails(orderC.details().stream().map(orderDetailMapper::toEntity).toList());
        return order;
    }

    public OrderDto toDto (Orders order){
        return new OrderDto(
                order.getId(),
                order.getDate(),
                order.getState(),
                order.getPayment(),
                order.getDelivery(),
                order.getTotal(),
                order.getDetails().stream().map(orderDetailMapper::toDto).toList()
        );
    }
}
