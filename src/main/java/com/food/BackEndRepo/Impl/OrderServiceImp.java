package com.food.BackEndRepo.Impl;

import com.food.BackEndRepo.entity.Orders;
import com.food.BackEndRepo.entity.dto.order.OrderCreate;
import com.food.BackEndRepo.entity.dto.order.OrderDto;
import com.food.BackEndRepo.entity.dto.order.OrderEdit;
import com.food.BackEndRepo.entity.dto.orderDetail.OrderDetailCreate;
import com.food.BackEndRepo.entity.dto.product.ProductDto;
import com.food.BackEndRepo.entity.mapper.OrderMapper;
import com.food.BackEndRepo.repository.OrderRepository;
import com.food.BackEndRepo.service.OrderService;
import com.food.BackEndRepo.service.ProductService;
import com.food.BackEndRepo.service.UserService;
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
    UserService userService;

    @Autowired
    ProductService productService;

    @Override
    public OrderDto save(OrderCreate orderCreate, Long idUser) {
        List<Integer> amount = orderCreate.details().stream().map(OrderDetailCreate::amount).toList();
        List<Long> product = orderCreate.details().stream().map(OrderDetailCreate::productId).toList();
        for (int i = 0; i < amount.size(); i++) {
            Integer amt = amount.get(i);
            Long proId = product.get(i);
            if (!productService.checkStock(proId, amt)){
                ProductDto productDto = productService.findById(proId);
                throw new IllegalArgumentException("No hay stock suficiente para el producto: " + productDto.getName());
            }
        }
        Orders orders = orderMapper.toEntity(orderCreate);
        orders = orderRepository.save(orders);
        userService.orderAdd(idUser, orders);
        for (int i = 0; i < amount.size(); i++) {
            Integer amt = amount.get(i);
            Long proId = product.get(i);
            productService.subtractStock(proId, amt);
        }
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
