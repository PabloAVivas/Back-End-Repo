package com.food.BackEndRepo.Impl;

import com.food.BackEndRepo.entity.OrderDetail;
import com.food.BackEndRepo.entity.Orders;
import com.food.BackEndRepo.entity.dto.order.OrderCreate;
import com.food.BackEndRepo.entity.dto.order.OrderDto;
import com.food.BackEndRepo.entity.dto.order.OrderEdit;
import com.food.BackEndRepo.entity.mapper.OrderMapper;
import com.food.BackEndRepo.repository.OrderDetailRepository;
import com.food.BackEndRepo.repository.OrderRepository;
import com.food.BackEndRepo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDto save(OrderCreate orderCreate) {
        Orders orders = orderMapper.toEntity(orderCreate);
        orders = orderRepository.save(orders);
        return orderMapper.toDto(orders);
    }

    @Override
    public OrderDto edit(OrderEdit orderEdit, Long id) {
        Orders orders = orderRepository.findById(id).orElseThrow(()-> new NullPointerException("The order with the id was not found " + id));
        orders.setState(orderEdit.getState());
        orderRepository.save(orders);
        return orderMapper.toDto(orders);
    }

    @Override
    public OrderDto detailAdd(Long id, Long idDetail) {
        Orders orders = orderRepository.findById(id).orElseThrow(()-> new NullPointerException("The order with the id was not found " + id));
        OrderDetail orderDetail = orderDetailRepository.findById(idDetail).orElseThrow(()-> new NullPointerException("The orderDetail with the id was not found " + id));
        orders.addOrderDetail(orderDetail);
        orderRepository.save(orders);
        return orderMapper.toDto(orders);
    }

    @Override
    public OrderDto findById(Long id) {
        return orderMapper.toDto(orderRepository.findById(id).orElseThrow(()-> new NullPointerException("The order with the id was not found " + id)));
    }

    @Override
    public List<OrderDto> findAllByDeletedFalse() {
        return orderRepository.findAllByDeletedFalse().stream().map(orderMapper::toDto).toList();
    }

    @Override
    public void delete(Long id) {
        Orders orders = orderRepository.findById(id).orElseThrow(()-> new NullPointerException("The order with the id was not found " + id));
        orders.setDeleted(!orders.isDeleted());
        orderRepository.save(orders);
    }
}
