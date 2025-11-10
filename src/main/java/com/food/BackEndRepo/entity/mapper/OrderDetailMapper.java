package com.food.BackEndRepo.entity.mapper;

import com.food.BackEndRepo.entity.OrderDetail;
import com.food.BackEndRepo.entity.dto.orderDetail.OrderDetailCreate;
import com.food.BackEndRepo.entity.dto.orderDetail.OrderDetailDto;
import com.food.BackEndRepo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailMapper {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductMapper productMapper;
    public OrderDetail toEntity (OrderDetailCreate orderDetailC){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setAmount(orderDetailC.amount());
        orderDetail.setSubtotal(orderDetailC.subtotal());
        orderDetail.setProduct(productRepository.findById(orderDetailC.productId()).get());
        return orderDetail;
    }

    public OrderDetailDto toDto (OrderDetail orderDetail){
        orderDetail.getProduct().getName();
        return new OrderDetailDto(
                orderDetail.getId(),
                orderDetail.getAmount(),
                orderDetail.getSubtotal(),
                orderDetail.getProduct().getName()
        );
    }
}
