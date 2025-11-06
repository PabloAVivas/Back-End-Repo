package com.food.BackEndRepo.service;

import com.food.BackEndRepo.entity.dto.order.OrderCreate;
import com.food.BackEndRepo.entity.dto.order.OrderDto;
import com.food.BackEndRepo.entity.dto.order.OrderEdit;

import java.util.ArrayList;
import java.util.List;

public interface OrderService {
    public OrderDto save (OrderCreate orderCreate);
    public OrderDto edit (OrderEdit orderEdit, Long id);
    public OrderDto detailAdd (Long id, Long idDetail);
    public OrderDto findById (Long id);
    public List<OrderDto> findAllByDeletedFalse();
    public void delete (Long id);
}
