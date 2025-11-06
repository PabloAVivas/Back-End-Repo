package com.food.BackEndRepo.service;

import com.food.BackEndRepo.entity.dto.orderDetail.OrderDetailCreate;
import com.food.BackEndRepo.entity.dto.orderDetail.OrderDetailDto;
import com.food.BackEndRepo.entity.dto.orderDetail.OrderDetailEdit;

import java.util.List;

public interface OrderDetailService {
    public OrderDetailDto save (OrderDetailCreate orderDetailCreate);
    public OrderDetailDto edit (OrderDetailEdit orderDetailEdit, Long id);
    public OrderDetailDto findById (Long id);
    public List<OrderDetailDto> findAllByDeletedFalse();
    public void delete (Long id);
}
