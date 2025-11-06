package com.food.BackEndRepo.Impl;

import com.food.BackEndRepo.entity.OrderDetail;
import com.food.BackEndRepo.entity.Product;
import com.food.BackEndRepo.entity.dto.orderDetail.OrderDetailCreate;
import com.food.BackEndRepo.entity.dto.orderDetail.OrderDetailDto;
import com.food.BackEndRepo.entity.dto.orderDetail.OrderDetailEdit;
import com.food.BackEndRepo.entity.mapper.OrderDetailMapper;
import com.food.BackEndRepo.repository.OrderDetailRepository;
import com.food.BackEndRepo.repository.ProductRepository;
import com.food.BackEndRepo.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImp implements OrderDetailService {
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    OrderDetailMapper orderDetailMapper;

    @Autowired
    ProductRepository productRepository;

    @Override
    public OrderDetailDto save(OrderDetailCreate orderDetailCreate) {
        OrderDetail orderDetail = orderDetailMapper.toEntity(orderDetailCreate);
        if(orderDetail.getProduct().getStock()>orderDetail.getAmount()) {
            orderDetail = orderDetailRepository.save(orderDetail);
            Long id = orderDetail.getProduct().getId();
            Product product = productRepository.findById(id).orElseThrow(()-> new NullPointerException("The product with the id was not found " + id));
            product.setStock(product.getStock()-orderDetail.getAmount());
            return orderDetailMapper.toDto(orderDetail);
        }else {
            return null;
        }
    }

    @Override
    public OrderDetailDto edit(OrderDetailEdit orderDetailEdit, Long id) {
        return null;
    }

    @Override
    public OrderDetailDto findById(Long id) {
        return orderDetailMapper.toDto(orderDetailRepository.findById(id).orElseThrow(()-> new NullPointerException("The orderDetail with the id was not found " + id)));
    }

    @Override
    public List<OrderDetailDto> findAllByDeletedFalse() {
        return orderDetailRepository.findAllByDeletedFalse().stream().map(orderDetailMapper::toDto).toList();
    }

    @Override
    public void delete(Long id) {
        OrderDetail orderDetail = orderDetailRepository.findById(id).orElseThrow(()-> new NullPointerException("The orderDetail with the id was not found " + id));
        orderDetail.setDeleted(!orderDetail.isDeleted());
        orderDetailRepository.save(orderDetail);
    }
}
