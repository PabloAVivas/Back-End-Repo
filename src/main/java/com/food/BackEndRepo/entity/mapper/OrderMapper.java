package com.food.BackEndRepo.entity.mapper;

import com.food.BackEndRepo.entity.Orders;
import com.food.BackEndRepo.entity.dto.order.OrderCreate;
import com.food.BackEndRepo.entity.dto.order.OrderDto;
import com.food.BackEndRepo.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;

import static com.food.BackEndRepo.entity.dto.enums.State.PENDING;

@Component
public class OrderMapper {
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    OrderDetailMapper orderDetailMapper;

    public Orders toEntity (OrderCreate orderC){
        Orders order = new Orders();
        order.setDate(LocalDate.now());
        order.setState(PENDING);
        order.setTotal(0);
        order.setDetails(new ArrayList<>());
        return order;
    }

    public OrderDto toDto (Orders order){
        return new OrderDto(
                order.getId(),
                order.getDate(),
                order.getState(),
                order.getTotal(),
                order.getDetails().stream().map(orderDetailMapper::toDto).toList()
        );
    }
}
