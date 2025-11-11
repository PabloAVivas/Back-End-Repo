package com.food.BackEndRepo.Impl;

import com.food.BackEndRepo.entity.Orders;
import com.food.BackEndRepo.entity.dto.order.OrderCreate;
import com.food.BackEndRepo.entity.dto.order.OrderDto;
import com.food.BackEndRepo.entity.dto.order.OrderEdit;
import com.food.BackEndRepo.entity.dto.orderDetail.OrderDetailCreate;
import com.food.BackEndRepo.entity.dto.orderDetail.OrderDetailDto;
import com.food.BackEndRepo.entity.dto.product.ProductDto;
import com.food.BackEndRepo.entity.mapper.OrderMapper;
import com.food.BackEndRepo.repository.OrderRepository;
import com.food.BackEndRepo.service.OrderService;
import com.food.BackEndRepo.service.ProductService;
import com.food.BackEndRepo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import static com.food.BackEndRepo.entity.dto.enums.State.CANCELED;

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

    //Recibe parametros para crear un perdido con sus detalles de pedidos
    //verifica que la cantidad de un producto este disponible para el pedido
    //en caso de no haber stock sufuciente se lanzara un IllegalArgumentException
    //en caso de haber stock en todos los detalles, se creara y guardara el pedido, restando el stock al producto
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

    //Recibe parametros para actualizar el estado de un pedido
    //Si el pedido pasa a CANCELED se llama a una funcion para devolver el stock
    @Override
    public OrderDto edit(OrderEdit orderEdit, Long id) {
        Orders orders = orderRepository.findById(id).orElseThrow(()-> new NullPointerException("The order with the id was not found " + id));
        orders.setState(orderEdit.getState());
        if (orders.getState() == CANCELED){
            for (int i = 0; i < orders.getDetails().size(); i++){
                productService.addStock(orders.getDetails().get(i).getProduct().getId(), orders.getDetails().get(i).getAmount());
            }
        }
        orderRepository.save(orders);
        return orderMapper.toDto(orders);
    }

    //Recibe parametros para buscar un pedido mediante su id
    @Override
    public OrderDto findById(Long id) {
        return orderMapper.toDto(orderRepository.findById(id).orElseThrow(()-> new NullPointerException("The order with the id was not found " + id)));
    }

    //Busca y devuelve todos los pedidos que no esten eliminados
    @Override
    public List<OrderDto> findAllByDeletedFalse() {
        return orderRepository.findAllByDeletedFalse().stream().map(orderMapper::toDto).toList();
    }

    @Override
    public List<OrderDto> findOrdersByUserIdAndNotDeleted(Long userId) {
        return orderRepository.findOrdersByUserIdAndNotDeleted(userId).stream().map(orderMapper::toDto).toList();
    }

    //Recibe el id de un pedido para "eliminarlo" pero no de la base de datos
    @Override
    public void delete(Long id) {
        Orders orders = orderRepository.findById(id).orElseThrow(()-> new NullPointerException("The order with the id was not found " + id));
        orders.setDeleted(!orders.isDeleted());
        orderRepository.save(orders);
    }
}
